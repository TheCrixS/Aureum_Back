package aureum.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "servicios")
public class Servicio extends PanacheEntity {

    @Column(nullable = false)
    public String nombre;

    public String descripcion;

    @Column(nullable = false)
    public int duracionMinutos;

    @Column(nullable = false, precision = 10, scale = 2)
    public BigDecimal precioBase;

    @Column(nullable = false)
    public boolean activo = true;
}
