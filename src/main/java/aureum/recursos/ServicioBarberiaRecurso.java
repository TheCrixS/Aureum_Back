package aureum.recursos;

import aureum.dto.RegistroServicioDTO;
import aureum.dto.RespuestaMensajeDTO;
import aureum.dto.ServicioRespuestaDTO;
import aureum.excepciones.ValidacionException;
import aureum.servicios.ServicioBarberiaServicio;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/servicios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioBarberiaRecurso {

    private static final Logger LOG = Logger.getLogger(ServicioBarberiaRecurso.class);

    @Inject
    ServicioBarberiaServicio servicioBarberiaServicio;

    @POST
    public Response guardar(RegistroServicioDTO registroServicioDTO) {
        try {
            ServicioRespuestaDTO servicioGuardado = servicioBarberiaServicio.guardar(registroServicioDTO);
            RespuestaMensajeDTO respuesta = new RespuestaMensajeDTO(
                    true,
                    "El servicio ha sido registrado exitosamente.",
                    servicioGuardado
            );
            return Response.status(Response.Status.CREATED).entity(respuesta).build();
        } catch (ValidacionException exception) {
            throw exception;
        } catch (Exception exception) {
            LOG.error("Error al guardar el servicio", exception);
            RespuestaMensajeDTO respuesta = new RespuestaMensajeDTO(
                    false,
                    "Ha ocurrido un error al guardar el servicio. Intente nuevamente.",
                    null
            );
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
}
