package aureum.dominio.modelo;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Barbero")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BarberoEntity extends PanacheEntity {
    public String nombres;
    public String apellidos;
    public String tipoIdentificacion;
    public Long identificacion;
    public Long telefono;
    public String email;
    public String especialidad;
    public Boolean estado;
}
