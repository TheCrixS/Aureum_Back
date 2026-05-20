package aureum.repositorios;

import aureum.entidades.ServicioBarberia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ServicioBarberiaRepositorio implements PanacheRepository<ServicioBarberia> {

    public boolean existePorNombreNormalizado(String nombreNormalizado) {
        return count("nombreNormalizado", nombreNormalizado) > 0;
    }
}
