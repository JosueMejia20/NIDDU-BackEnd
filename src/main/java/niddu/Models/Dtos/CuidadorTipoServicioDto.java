package niddu.Models.Dtos;

import lombok.Data;
import java.util.List;

@Data
public class CuidadorTipoServicioDto {
    private int idCuidador;
    private List<Integer> idTipoServicio;
}
