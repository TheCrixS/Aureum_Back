package aureum.dominio.modelo;

import lombok.Builder;

@Builder
public class Barbero {
    public String nombres;
    public String apellidos;
    public String tipoIdentificacion;
    public Long identificacion;
    public Long telefono;
    public String email;
    public String especialidad;
    public Boolean estado;
}
