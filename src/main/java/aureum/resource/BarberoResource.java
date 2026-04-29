package aureum.resource;

import aureum.dto.BarberoDisponibleDTO;
import aureum.dto.HorarioDisponibleDTO;
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
