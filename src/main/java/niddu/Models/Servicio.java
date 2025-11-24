package niddu.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicios")
    private int idServicios;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_cuidador")
    private Cuidador cuidador;

    @ManyToOne
    @JoinColumn(name = "id_mascota")
    private Mascota mascota;


    @ManyToOne
    @JoinColumn(name = "id_tipo_servicio")
    private TipoServicio idTipoServicio;
}
