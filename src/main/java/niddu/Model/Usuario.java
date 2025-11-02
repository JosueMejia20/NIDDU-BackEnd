package niddu.Model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

    @OneToOne(mappedBy = "usuario")
    @JsonIgnore
    private Persona persona;

    @Column(name = "nombre_usuario", nullable = false, unique = true)
    private String userName;

    @ManyToOne
    @JoinColumn(name = "id_tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "estado")
    private String estado = EstadoUsuario.ACTIVO.name();
}
