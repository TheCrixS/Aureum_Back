package aureum.repository;

import aureum.entity.Servicio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ServicioRepository implements PanacheRepository<Servicio> {

    public List<Servicio> findActivos() {
        return list("activo", true);
    }
}
