package aureum.excepciones;

import java.util.List;
import java.util.Map;

public class ValidacionException extends RuntimeException {

    private final Map<String, List<String>> errores;

    public ValidacionException(Map<String, List<String>> errores) {
        super("Hay errores de validacion en los datos enviados.");
        this.errores = errores;
    }

    public Map<String, List<String>> getErrores() {
        return errores;
    }
}
