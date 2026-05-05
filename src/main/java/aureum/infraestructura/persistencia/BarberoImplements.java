package aureum.infraestructura.persistencia;

import aureum.dominio.modelo.Barbero;
import aureum.dominio.modelo.BarberoEntity;
import aureum.dominio.repositorio.BarberoRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

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
    }
}
