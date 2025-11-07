package niddu.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private int idDepartamento;

    @Column(name = "nombre_departamento", nullable = false)
    private String nombreDepartamento;
}
