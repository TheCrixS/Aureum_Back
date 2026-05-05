package aureum.aplicacion;

import aureum.dominio.modelo.Barbero;
import aureum.dominio.repositorio.BarberoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BarberoService {
    @Inject
    BarberoRepository  barberoRepository;
    public void crearBarbero(Barbero barbero){
        barberoRepository.crearBarbero(barbero);
    }
}
