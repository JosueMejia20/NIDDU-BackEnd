package niddu.Model;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "personas")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private int id;

    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private Timestamp fechaCreacion;

    @Column(name = "fecha_actualizacion", insertable = false, updatable = false)
    private Timestamp fechaActualizacion;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", unique = true)
    private Usuario usuario;
}
