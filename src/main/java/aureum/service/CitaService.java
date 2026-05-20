package aureum.service;

import aureum.dto.CitaRequestDTO;
import aureum.dto.CitaResponseDTO;
import aureum.entidades.ServicioBarberia;
import aureum.entity.*;
import aureum.repository.BarberoRepository;
import aureum.repository.CitaRepository;
import aureum.repository.ServicioRepository;
import aureum.repositorios.ServicioBarberiaRepositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class CitaService {

    @Inject CitaRepository citaRepository;
    @Inject BarberoRepository barberoRepository;
    @Inject ServicioBarberiaRepositorio servicioBarberiaRepositorio;
    @Inject ServicioRepository servicioRepository;
    @Inject EntityManager entityManager;

    @Transactional
    public CitaResponseDTO crearCita(CitaRequestDTO req) {
        // CA-08: fecha no puede ser pasada
        if (req.fecha.isBefore(java.time.LocalDate.now())) {
            throw new IllegalArgumentException(
                "No es posible agendar en una fecha pasada.");
        }

        ServicioBarberia servicio = servicioBarberiaRepositorio.findById(req.servicioId);
        Barbero barbero = barberoRepository.findById(req.barberoId);
        Usuario cliente = Usuario.findById(req.clienteId);
        if (cliente == null) {
            cliente = obtenerClientePrueba();
        }

        if (servicio == null || barbero == null) {
            throw new IllegalArgumentException(
                "Servicio, barbero o cliente no encontrado.");
        }

        asegurarServicioEnTablaCitas(servicio);

        java.time.LocalTime horaFin =
            req.horaInicio.plusMinutes(servicio.getDuracionMinutos());

        // CA-12: validar conflicto con transacción (RNF05)
        boolean conflicto = citaRepository.existeConflicto(
            req.barberoId, req.fecha, req.horaInicio, horaFin);
        if (conflicto) {
            throw new IllegalStateException(
                "Este horario ya no está disponible. Por favor, selecciona otro.");
        }

        Cita cita = new Cita();
        cita.cliente = cliente;
        cita.barbero = barbero;
        cita.servicio = servicio;
        cita.fecha = req.fecha;
        cita.horaInicio = req.horaInicio;
        cita.horaFin = horaFin;
        cita.estado = Cita.EstadoCita.PENDIENTE;
        cita.notas = req.notas;
        citaRepository.persist(cita);

        return toDTO(cita,
            "Tu cita ha sido agendada exitosamente. Recibirás una confirmación en breve.");
    }

    private void asegurarServicioEnTablaCitas(ServicioBarberia servicioBarberia) {
        Servicio servicioCita = servicioRepository.findById(servicioBarberia.getId());

        if (servicioCita == null) {
            entityManager.createNativeQuery("""
                    INSERT INTO servicios (id, nombre, descripcion, duracionminutos, preciobase, activo)
                    VALUES (?1, ?2, ?3, ?4, ?5, ?6)
                    ON CONFLICT (id) DO NOTHING
                    """)
                .setParameter(1, servicioBarberia.getId())
                .setParameter(2, servicioBarberia.getNombre())
                .setParameter(3, limitarTexto(servicioBarberia.getDescripcion(), 255))
                .setParameter(4, servicioBarberia.getDuracionMinutos())
                .setParameter(5, servicioBarberia.getPrecio())
                .setParameter(6, true)
                .executeUpdate();
        }
    }

    private String limitarTexto(String texto, int maximo) {
        if (texto == null || texto.length() <= maximo) {
            return texto;
        }

        return texto.substring(0, maximo);
    }

    private Usuario obtenerClientePrueba() {
        Usuario cliente = Usuario.find("email", "cliente.prueba@aureum.com").firstResult();

        if (cliente == null) {
            cliente = new Usuario();
            cliente.email = "cliente.prueba@aureum.com";
            cliente.passwordHash = "temporal";
            cliente.nombre = "Cliente Prueba";
            cliente.telefono = "3000000000";
            cliente.rol = Usuario.Rol.CLIENTE;
            cliente.activo = true;
            cliente.persist();
        }

        return cliente;
    }

    private CitaResponseDTO toDTO(Cita c, String mensaje) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
        CitaResponseDTO dto = new CitaResponseDTO();
        dto.id = c.id;
        dto.clienteNombre = c.cliente.nombre;
        dto.barberoNombre = c.barbero.usuario.nombre;
        dto.servicioNombre = c.servicio.getNombre();
        dto.fecha = c.fecha;
        dto.horaInicio = c.horaInicio;
        dto.horaFin = c.horaFin;
        dto.precio = c.servicio.getPrecio();
        dto.estado = c.estado.name();
        dto.mensaje = mensaje;
        return dto;
    }
}
