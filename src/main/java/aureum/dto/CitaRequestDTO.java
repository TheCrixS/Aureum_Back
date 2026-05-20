package aureum.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaRequestDTO {
    public Long clienteId;
    public Long barberoId;
    public Long servicioId;
    public LocalDate fecha;
    public LocalTime horaInicio;
    public String notas;
}
