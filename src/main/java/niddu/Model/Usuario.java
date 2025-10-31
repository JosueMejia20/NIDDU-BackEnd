package niddu.Model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Usuarios")
public class Usuario {

    public enum EstadoUsuario {
        ACTIVO,
        DESACTIVADO,
        SUSPENDIDO
    }

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "id_tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasenia;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "fecha_registro", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaRegistro;

    @JoinColumn(name = "estado")
    private String estado = EstadoUsuario.ACTIVO.name();
}
