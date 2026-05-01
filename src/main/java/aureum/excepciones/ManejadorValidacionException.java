package aureum.excepciones;

import aureum.dto.RespuestaErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ManejadorValidacionException implements ExceptionMapper<ValidacionException> {

    @Override
    public Response toResponse(ValidacionException exception) {
        RespuestaErrorDTO respuesta = new RespuestaErrorDTO(
                false,
                "No fue posible registrar el servicio. Revise los campos marcados.",
                exception.getErrores()
        );
        return Response.status(Response.Status.BAD_REQUEST).entity(respuesta).build();
    }
}
