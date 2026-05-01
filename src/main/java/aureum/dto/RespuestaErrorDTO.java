package aureum.dto;

import java.util.List;
import java.util.Map;

public class RespuestaErrorDTO {

    private boolean exito;
    private String mensaje;
    private Map<String, List<String>> errores;

    public RespuestaErrorDTO() {
    }

    public RespuestaErrorDTO(boolean exito, String mensaje, Map<String, List<String>> errores) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.errores = errores;
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

    public Map<String, List<String>> getErrores() {
        return errores;
    }

    public void setErrores(Map<String, List<String>> errores) {
        this.errores = errores;
    }
}
