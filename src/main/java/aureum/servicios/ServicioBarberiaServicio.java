package aureum.servicios;

import aureum.dto.RegistroServicioDTO;
import aureum.dto.ServicioRespuestaDTO;
import aureum.entidades.ServicioBarberia;
import aureum.excepciones.ValidacionException;
import aureum.repositorios.ServicioBarberiaRepositorio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@ApplicationScoped
public class ServicioBarberiaServicio {

    @Inject
    ServicioBarberiaRepositorio servicioBarberiaRepositorio;

    @Transactional
    public ServicioRespuestaDTO guardar(RegistroServicioDTO registroServicioDTO) {
        Map<String, List<String>> errores = validar(registroServicioDTO);

        if (!errores.isEmpty()) {
            throw new ValidacionException(errores);
        }

        String nombre = registroServicioDTO.getNombre().trim();
        String descripcion = registroServicioDTO.getDescripcion().trim();

        ServicioBarberia servicioBarberia = new ServicioBarberia();
        servicioBarberia.setNombre(nombre);
        servicioBarberia.setNombreNormalizado(normalizarNombre(nombre));
        servicioBarberia.setDescripcion(descripcion);
        servicioBarberia.setDuracionMinutos(Integer.valueOf(registroServicioDTO.getDuracionMinutos().trim()));
        servicioBarberia.setPrecio(convertirPrecio(registroServicioDTO.getPrecio()));

        servicioBarberiaRepositorio.persist(servicioBarberia);

        return convertirARespuesta(servicioBarberia);
    }

    private Map<String, List<String>> validar(RegistroServicioDTO registroServicioDTO) {
        Map<String, List<String>> errores = new LinkedHashMap<>();

        if (registroServicioDTO == null) {
            agregarError(errores, "servicio", "Debe enviar los datos del servicio.");
            return errores;
        }

        validarNombre(registroServicioDTO.getNombre(), errores);
        validarDescripcion(registroServicioDTO.getDescripcion(), errores);
        validarDuracion(registroServicioDTO.getDuracionMinutos(), errores);
        validarPrecio(registroServicioDTO.getPrecio(), errores);

        return errores;
    }

    private void validarNombre(String nombre, Map<String, List<String>> errores) {
        if (estaVacio(nombre)) {
            agregarError(errores, "nombre", "El nombre del servicio no debe estar vacio.");
            return;
        }

        String nombreLimpio = nombre.trim();

        if (nombreLimpio.length() > 100) {
            agregarError(errores, "nombre", "El nombre del servicio no debe superar los 100 caracteres.");
        }

        if (servicioBarberiaRepositorio.existePorNombreNormalizado(normalizarNombre(nombreLimpio))) {
            agregarError(errores, "nombre",
                    "Ya existe un servicio con este nombre. Por favor, use un nombre diferente.");
        }
    }

    private void validarDescripcion(String descripcion, Map<String, List<String>> errores) {
        if (estaVacio(descripcion)) {
            agregarError(errores, "descripcion",
                    "La descripcion no debe estar vacia y debe contener al menos 10 caracteres.");
            return;
        }

        String descripcionLimpia = descripcion.trim();

        if (descripcionLimpia.length() < 10) {
            agregarError(errores, "descripcion", "La descripcion debe contener al menos 10 caracteres.");
        }

        if (descripcionLimpia.length() > 500) {
            agregarError(errores, "descripcion", "La descripcion no debe superar los 500 caracteres.");
        }
    }

    private void validarDuracion(String duracion, Map<String, List<String>> errores) {
        if (estaVacio(duracion)) {
            agregarError(errores, "duracionMinutos",
                    "La duracion no debe estar vacia. Debe ingresar un numero entero positivo.");
            return;
        }

        String duracionLimpia = duracion.trim();

        if (!duracionLimpia.matches("\\d+")) {
            agregarError(errores, "duracionMinutos",
                    "La duracion solo permite numeros enteros positivos, sin letras, simbolos ni decimales.");
            return;
        }

        int duracionNumerica;

        try {
            duracionNumerica = Integer.parseInt(duracionLimpia);
        } catch (NumberFormatException exception) {
            agregarError(errores, "duracionMinutos",
                    "La duracion debe estar entre 5 y 480 minutos.(8 Horas)");
            return;
        }

        if (duracionNumerica < 5 || duracionNumerica > 480) {
            agregarError(errores, "duracionMinutos", "La duracion debe estar entre 5 y 480 minutos.(8 Horas)");
        }
    }

    private void validarPrecio(String precio, Map<String, List<String>> errores) {
        if (estaVacio(precio)) {
            agregarError(errores, "precio", "El precio no debe estar vacio.");
            return;
        }

        try {
            BigDecimal precioNumerico = convertirPrecio(precio);

            if (precioNumerico.compareTo(BigDecimal.ZERO) <= 0) {
                agregarError(errores, "precio", "El precio debe ser un valor positivo mayor a $0.");
            }
        } catch (NumberFormatException exception) {
            agregarError(errores, "precio", "El precio debe ser un valor positivo mayor a $0.");
        }
    }

    private BigDecimal convertirPrecio(String precio) {
        String precioLimpio = precio.trim()
                .replace("$", "")
                .replace(" ", "")
                .replace(".", "")
                .replace(",", ".");

        return new BigDecimal(precioLimpio);
    }

    private ServicioRespuestaDTO convertirARespuesta(ServicioBarberia servicioBarberia) {
        return new ServicioRespuestaDTO(
                servicioBarberia.getId(),
                servicioBarberia.getNombre(),
                servicioBarberia.getDescripcion(),
                servicioBarberia.getDuracionMinutos(),
                servicioBarberia.getPrecio(),
                formatearPrecio(servicioBarberia.getPrecio())
        );
    }

    private String formatearPrecio(BigDecimal precio) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols(Locale.of("es", "CO"));
        simbolos.setGroupingSeparator('.');
        simbolos.setDecimalSeparator(',');

        DecimalFormat formato = new DecimalFormat("#,##0.##", simbolos);
        return "$" + formato.format(precio);
    }

    private String normalizarNombre(String nombre) {
        return nombre.trim().toLowerCase(Locale.ROOT);
    }

    private boolean estaVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    private void agregarError(Map<String, List<String>> errores, String campo, String mensaje) {
        errores.computeIfAbsent(campo, llave -> new ArrayList<>()).add(mensaje);
    }
}
