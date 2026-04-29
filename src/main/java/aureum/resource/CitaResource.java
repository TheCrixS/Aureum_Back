package aureum.resource;

import aureum.dto.CitaRequestDTO;
import aureum.dto.CitaResponseDTO;
import aureum.service.CitaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/citas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CitaResource {

    @Inject CitaService citaService;

    @POST
    public Response crearCita(CitaRequestDTO request) {
        try {
            CitaResponseDTO response = citaService.crearCita(request);
            return Response.status(Response.Status.CREATED).entity(response).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.CONFLICT)
                .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("{\"error\": \"Ha ocurrido un error al agendar tu cita. Intenta nuevamente.\"}").build();
        }
    }
}
