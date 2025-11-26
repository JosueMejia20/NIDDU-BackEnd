package niddu.Models.Dtos;

import lombok.Data;
import niddu.Models.Cuidador;
import niddu.Models.TipoServicio;
import java.util.List;

@Data
public class CuidadorTipoServicioDto {
    private Cuidador cuidador;
    private List<TipoServicio> TipoServicios;
}
