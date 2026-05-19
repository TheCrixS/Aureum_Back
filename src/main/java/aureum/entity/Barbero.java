package aureum.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "barberos")
public class Barbero extends PanacheEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    public Usuario usuario;

    @Column(precision = 5, scale = 2)
    public BigDecimal porcentajeComision = BigDecimal.valueOf(30);

    @Column(nullable = false)
    public boolean activo = true;

    @OneToMany(mappedBy = "barbero", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<HorarioLaboral> horarios;
}
