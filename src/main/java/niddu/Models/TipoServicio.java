package niddu.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tiposervicio")
public class TipoServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tiposervicio")
    private int idTipoServicio;

    @Column(name = "descripcion")
    private String descripcion;
}
