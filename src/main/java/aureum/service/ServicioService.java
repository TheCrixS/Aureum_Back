package aureum.service;

import aureum.dto.ServicioDTO;
import aureum.entity.Servicio;
import aureum.repository.ServicioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ServicioService {

    @Inject
    ServicioRepository servicioRepository;

    public List<ServicioDTO> listarActivos() {
        return servicioRepository.findActivos()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    private ServicioDTO toDTO(Servicio s) {
        ServicioDTO dto = new ServicioDTO();
        dto.id = s.id;
        dto.nombre = s.nombre;
        dto.descripcion = s.descripcion;
        dto.duracionMinutos = s.duracionMinutos;
        dto.precioBase = s.precioBase;
        return dto;
    }
}
