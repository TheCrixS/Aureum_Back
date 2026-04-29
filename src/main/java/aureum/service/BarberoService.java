package aureum.service;

import aureum.dto.BarberoDisponibleDTO;
import aureum.dto.HorarioDisponibleDTO;
import aureum.entity.Barbero;
import aureum.entity.Cita;
import aureum.entity.HorarioLaboral;
import aureum.entity.Servicio;
import aureum.repository.BarberoRepository;
import aureum.repository.CitaRepository;
import aureum.repository.ServicioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BarberoService {

    @Inject BarberoRepository barberoRepository;
    @Inject CitaRepository citaRepository;
    @Inject ServicioRepository servicioRepository;

    public List<BarberoDisponibleDTO> findDisponibles(LocalDate fecha, Long servicioId) {
        return barberoRepository.findDisponiblesPorFecha(fecha)
            .stream()
            .map(b -> {
                BarberoDisponibleDTO dto = new BarberoDisponibleDTO();
                dto.id = b.id;
                dto.nombre = b.usuario.nombre;
                dto.telefono = b.usuario.telefono;
                return dto;
            })
            .collect(Collectors.toList());
    }

    public List<HorarioDisponibleDTO> findHorariosDisponibles(
            Long barberoId, LocalDate fecha, Long servicioId) {

        Barbero barbero = barberoRepository.findById(barberoId);
        Servicio servicio = servicioRepository.findById(servicioId);
        if (barbero == null || servicio == null) return List.of();

        int diaSemana = fecha.getDayOfWeek().getValue();
        HorarioLaboral horario = barbero.horarios.stream()
            .filter(h -> h.diaSemana == diaSemana)
            .findFirst().orElse(null);
        if (horario == null) return List.of();

        List<Cita> citasDelDia = citaRepository.findPorBarberoYFecha(barberoId, fecha);
        int duracion = servicio.duracionMinutos;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");
        List<HorarioDisponibleDTO> disponibles = new ArrayList<>();

        LocalTime cursor = horario.horaInicio;
        while (!cursor.plusMinutes(duracion).isAfter(horario.horaFin)) {
            final LocalTime inicio = cursor;
            final LocalTime fin = cursor.plusMinutes(duracion);
            boolean ocupado = citasDelDia.stream().anyMatch(c ->
                c.horaInicio.isBefore(fin) && c.horaFin.isAfter(inicio));
            if (!ocupado) {
                HorarioDisponibleDTO dto = new HorarioDisponibleDTO();
                dto.horaInicio = inicio;
                dto.horaFin = fin;
                dto.display = inicio.format(fmt) + " - " + fin.format(fmt);
                disponibles.add(dto);
            }
            cursor = cursor.plusMinutes(duracion);
        }
        return disponibles;
    }
}
