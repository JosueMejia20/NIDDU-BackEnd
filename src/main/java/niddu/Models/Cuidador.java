package niddu.Models;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cuidador")
public class Cuidador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuidador")
    private int idCuidador;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String contrasena;

    @Column(name = "id_estado_cuidador", nullable = false)
    private int idEstadoCuidador;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "foto_identidad")
    private String fotoIdentidad;

    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    private Persona persona;

    @Column(nullable = false)
    private int precioxhora;

    @Column(nullable = false)
    private int experiencia;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @JsonIgnore
    @OneToMany(mappedBy = "cuidador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Direccion> direcciones;


}
