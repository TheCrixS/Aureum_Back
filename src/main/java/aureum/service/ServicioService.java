package aureum.service;

import aureum.dto.ServicioDTO;
import aureum.entidades.ServicioBarberia;
import aureum.repositorios.ServicioBarberiaRepositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ServicioService {

    @Inject
    ServicioBarberiaRepositorio servicioBarberiaRepositorio;

    public List<ServicioDTO> listarActivos() {
        return servicioBarberiaRepositorio.listAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    private ServicioDTO toDTO(ServicioBarberia s) {
        ServicioDTO dto = new ServicioDTO();
        dto.id = s.getId();
        dto.nombre = s.getNombre();
        dto.descripcion = s.getDescripcion();
        dto.duracionMinutos = s.getDuracionMinutos();
        dto.precioBase = s.getPrecio();
        return dto;
    }
}
