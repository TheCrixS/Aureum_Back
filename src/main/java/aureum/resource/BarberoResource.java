package aureum.resource;

import aureum.dto.BarberoDisponibleDTO;
import aureum.dto.HorarioDisponibleDTO;
import aureum.entity.Barbero;
import aureum.repository.BarberoRepository;
import aureum.service.BarberoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("/api/barberos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BarberoResource {

    @Inject BarberoService barberoService;
    @Inject BarberoRepository barberoRepository;

    @GET
    @Path("/disponibles")
    public Response getDisponibles(
            @QueryParam("fecha") String fechaStr,
            @QueryParam("servicioId") Long servicioId) {
        LocalDate fecha = LocalDate.parse(fechaStr);
        List<BarberoDisponibleDTO> lista =
            barberoService.findDisponibles(fecha, servicioId);
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{barberoId}/dias-laborales")
    public Response getDiasLaborales(@PathParam("barberoId") Long barberoId) {
        Barbero barbero = barberoRepository.findById(barberoId);
        if (barbero == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Integer> dias = barbero.horarios.stream()
            .map(h -> h.diaSemana)
            .distinct()
            .sorted()
            .collect(java.util.stream.Collectors.toList());
        return Response.ok(dias).build();
    }

    @GET
    @Path("/{barberoId}/horarios-disponibles")
    public Response getHorariosDisponibles(
            @PathParam("barberoId") Long barberoId,
            @QueryParam("fecha") String fechaStr,
            @QueryParam("servicioId") Long servicioId) {
        LocalDate fecha = LocalDate.parse(fechaStr);
        List<HorarioDisponibleDTO> lista =
            barberoService.findHorariosDisponibles(barberoId, fecha, servicioId);
        return Response.ok(lista).build();
    }
}
