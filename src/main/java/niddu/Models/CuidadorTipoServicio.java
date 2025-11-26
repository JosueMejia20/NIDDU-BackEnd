package niddu.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cuidadortiposervicio")
@IdClass(CuidadorTipoServicioId.class)
public class CuidadorTipoServicio {
    @Id
    @Column(name = "id_cuidador")
    private int idCuidador;

    @Id
    @Column(name = "id_tiposervicio")
    private int idTipoServicio;

    @ManyToOne
    @JoinColumn(name = "id_cuidador", insertable = false, updatable = false)
    @JsonIgnore
    private Cuidador cuidador;

    @ManyToOne
    @JoinColumn(name = "id_tiposervicio", insertable = false, updatable = false)
    @JsonIgnore
    private TipoServicio tipoServicio;
}
