package aureum.infraestructura.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BarberoDto(
        @NotBlank(message = "El nombre es requerido")
        String nombres,
        @NotBlank(message = "El apellido es requerido")
        String apellidos,
        @NotBlank(message = "El tipo es requerido")
        String tipoIdentificacion,
        @NotBlank(message = "La identificacion es requerida")
        String identificacion,
        @Email(message = "El formato del correo es incorrecto")
        @NotBlank(message = "El email es requerido")
        String email,
        @NotNull(message = "El telefono es requerido")
        String telefono,
        @NotBlank(message = "El estado es requerido")
        Boolean estado
) {
}
