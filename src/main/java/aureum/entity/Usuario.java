package aureum.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario extends PanacheEntity {

    @Column(nullable = false, unique = true)
    public String email;

    @Column(nullable = false)
    public String passwordHash;

    @Column(nullable = false)
    public String nombre;

    public String telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Rol rol;

    @Column(nullable = false)
    public boolean activo = true;

    public enum Rol {
        ADMIN, BARBERO, CLIENTE
    }
}
