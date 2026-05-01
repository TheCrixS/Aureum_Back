package aureum.dto;

public class RespuestaMensajeDTO {

    private boolean exito;
    private String mensaje;
    private ServicioRespuestaDTO servicio;

    public RespuestaMensajeDTO() {
    }

    public RespuestaMensajeDTO(boolean exito, String mensaje, ServicioRespuestaDTO servicio) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.servicio = servicio;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ServicioRespuestaDTO getServicio() {
        return servicio;
    }

    public void setServicio(ServicioRespuestaDTO servicio) {
        this.servicio = servicio;
    }
}
