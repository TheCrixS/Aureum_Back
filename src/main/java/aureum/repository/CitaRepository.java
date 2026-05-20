package aureum.repository;

import aureum.entity.Cita;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ApplicationScoped
public class CitaRepository implements PanacheRepository<Cita> {

    public List<Cita> findPorBarberoYFecha(Long barberoId, LocalDate fecha) {
        return list("barbero.id = ?1 AND fecha = ?2 AND estado != ?3",
            barberoId, fecha, Cita.EstadoCita.CANCELADA);
    }

    public boolean existeConflicto(Long barberoId, LocalDate fecha,
                                    LocalTime horaInicio, LocalTime horaFin) {
        return count(
            "barbero.id = ?1 AND fecha = ?2 AND estado != ?3 " +
            "AND horaInicio < ?4 AND horaFin > ?5",
            barberoId, fecha, Cita.EstadoCita.CANCELADA,
            horaFin, horaInicio) > 0;
    }
}
