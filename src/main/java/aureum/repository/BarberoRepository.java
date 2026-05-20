package aureum.repository;

import aureum.entity.Barbero;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class BarberoRepository implements PanacheRepository<Barbero> {

    public List<Barbero> findActivos() {
        return list("activo", true);
    }

    public List<Barbero> findDisponiblesPorFecha(LocalDate fecha) {
        int diaSemana = fecha.getDayOfWeek().getValue();
        return getEntityManager().createQuery(
            "SELECT DISTINCT b FROM Barbero b " +
            "JOIN b.horarios h " +
            "WHERE b.activo = true AND h.diaSemana = :dia",
            Barbero.class)
            .setParameter("dia", diaSemana)
            .getResultList();
    }
}
