package niddu.Models;

import java.time.LocalDateTime;
<<<<<<< HEAD:src/main/java/niddu/Models/Usuario.java
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
=======
import jakarta.persistence.*;
>>>>>>> 3a6e192 (EndPoint de Usuarios actualizado):src/main/java/niddu/Model/Usuario.java
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {
<<<<<<< HEAD:src/main/java/niddu/Models/Usuario.java
=======

>>>>>>> 3a6e192 (EndPoint de Usuarios actualizado):src/main/java/niddu/Model/Usuario.java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "correo", nullable = false, unique = true)
    private String correo;
<<<<<<< HEAD:src/main/java/niddu/Models/Usuario.java

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "id_tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    @ManyToOne
    @JoinColumn(name = "id_estado_usuario", nullable = false)
    private EstadoUsuario estadoUsuario;
    
    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Direccion> direcciones = new ArrayList<>();
=======

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "id_tipo_usuario", nullable = false)
    private int idTipoUsuario;

    @Column(name = "id_estado_usuario", nullable = false)
    private int idEstadoUsuario;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "id_persona", nullable = false)
    private int idPersona;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fechaRegistro;
>>>>>>> 3a6e192 (EndPoint de Usuarios actualizado):src/main/java/niddu/Model/Usuario.java
}
