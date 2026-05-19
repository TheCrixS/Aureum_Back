package aureum.resource;

import aureum.dto.ServicioDTO;
import aureum.service.ServicioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/servicios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioResource {

    @Inject ServicioService servicioService;

    @GET
    @Path("/activos")
    public Response listarActivos() {
        List<ServicioDTO> lista = servicioService.listarActivos();
        return Response.ok(lista).build();
    }
}
