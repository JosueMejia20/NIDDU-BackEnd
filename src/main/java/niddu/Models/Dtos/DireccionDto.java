package niddu.Models.Dtos;
import lombok.Data;
import java.util.List;

import niddu.Models.Dtos.PersonaDto;

@Data

public class DireccionDto {
    
    private String ciudad;
    private String colonia;
    private DepartamentoDto departamento;

}
