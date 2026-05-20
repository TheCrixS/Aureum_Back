package aureum.entity;

import aureum.entidades.ServicioBarberia;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "citas")
public class Cita extends PanacheEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    public Usuario cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "barbero_id", nullable = false)
    public Barbero barbero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servicio_id", nullable = false)
    public ServicioBarberia servicio;

    @Column(nullable = false)
    public LocalDate fecha;

    @Column(nullable = false)
    public LocalTime horaInicio;

    @Column(nullable = false)
    public LocalTime horaFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public EstadoCita estado = EstadoCita.PENDIENTE;

    public String notas;

    public enum EstadoCita {
        PENDIENTE, CONFIRMADA, EN_PROCESO, FINALIZADA, CANCELADA
    }
}
