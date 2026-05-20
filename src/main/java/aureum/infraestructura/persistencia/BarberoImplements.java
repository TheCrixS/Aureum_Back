package aureum.infraestructura.persistencia;

import aureum.dominio.modelo.Barbero;
import aureum.dominio.modelo.BarberoEntity;
import aureum.dominio.repositorio.BarberoRepository;
import aureum.entity.HorarioLaboral;
import aureum.entity.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.time.LocalTime;

@ApplicationScoped
public class BarberoImplements implements BarberoRepository, PanacheRepository<BarberoEntity> {

    @Override
    @Transactional
    public void crearBarbero(Barbero barbero) {
        BarberoEntity  barberoEntity = BarberoEntity
                .builder()
                .nombres(barbero.nombres)
                .apellidos(barbero.apellidos)
                .tipoIdentificacion(barbero.tipoIdentificacion)
                .identificacion(barbero.identificacion)
                .email(barbero.email)
                .telefono(barbero.telefono)
                .estado(barbero.estado)
                .build();
        persist(barberoEntity);

        Usuario usuario = Usuario.find("email", barbero.email).firstResult();

        if (usuario == null) {
            usuario = new Usuario();
            usuario.email = barbero.email;
            usuario.passwordHash = "temporal";
            usuario.nombre = barbero.nombres + " " + barbero.apellidos;
            usuario.telefono = String.valueOf(barbero.telefono);
            usuario.rol = Usuario.Rol.BARBERO;
            usuario.activo = barbero.estado == null || barbero.estado;
            usuario.persist();
        }

        aureum.entity.Barbero barberoCita = aureum.entity.Barbero.find("usuario", usuario).firstResult();

        if (barberoCita == null) {
            barberoCita = new aureum.entity.Barbero();
            barberoCita.usuario = usuario;
            barberoCita.activo = barbero.estado == null || barbero.estado;
            barberoCita.persist();

            for (int dia = 1; dia <= 6; dia++) {
                HorarioLaboral horarioLaboral = new HorarioLaboral();
                horarioLaboral.barbero = barberoCita;
                horarioLaboral.diaSemana = dia;
                horarioLaboral.horaInicio = LocalTime.of(8, 0);
                horarioLaboral.horaFin = LocalTime.of(18, 0);
                horarioLaboral.persist();
            }
        }
    }
}
