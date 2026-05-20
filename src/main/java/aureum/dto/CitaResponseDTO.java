package aureum.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class CitaResponseDTO {
    public Long id;
    public String clienteNombre;
    public String barberoNombre;
    public String servicioNombre;
    public LocalDate fecha;
    public LocalTime horaInicio;
    public LocalTime horaFin;
    public BigDecimal precio;
    public String estado;
    public String mensaje;
}
