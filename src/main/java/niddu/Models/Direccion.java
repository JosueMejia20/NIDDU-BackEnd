package niddu.Models;

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
@Table(name = "direcciones")
public class Direccion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private int idDireccion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_departamento", referencedColumnName = "id_departamento")
    private Departamento departamento;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "colonia")
    private String colonia;
}
