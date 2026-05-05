package aureum.infraestructura;

import aureum.aplicacion.BarberoService;
import aureum.dominio.modelo.Barbero;
import aureum.infraestructura.dto.BarberoDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/crearBarbero")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BarberoResource {

    @Inject
    BarberoService  barberoService;

    @POST
    public Response createBarbero(BarberoDto  barberoDto) {
        Barbero barbero = Barbero
                .builder()
                .nombres(barberoDto.nombres())
                .apellidos(barberoDto.apellidos())
                .tipoIdentificacion(barberoDto.tipoIdentificacion())
                .identificacion(barberoDto.identificacion())
                .email(barberoDto.email())
                .telefono(barberoDto.telefono())
                .estado(barberoDto.estado())
                .build();
        barberoService.crearBarbero(barbero);
        return Response.status(Response.Status.CREATED).build();
    }
}
