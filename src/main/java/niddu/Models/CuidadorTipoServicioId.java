package niddu.Models;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;

@Embeddable
@Data
@Table(name = "cuidadortiposervicio")
public class CuidadorTipoServicioId implements Serializable {

    @Column(name = "id_cuidador")
    private int idCuidador;

    @Column(name = "id_tiposervicio")
    private int idTipoServicio;
}
