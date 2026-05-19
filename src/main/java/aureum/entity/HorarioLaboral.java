package aureum.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "horarios_laborales")
public class HorarioLaboral extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barbero_id", nullable = false)
    public Barbero barbero;

    @Column(nullable = false)
    public int diaSemana;

    @Column(nullable = false)
    public LocalTime horaInicio;

    @Column(nullable = false)
    public LocalTime horaFin;
}
