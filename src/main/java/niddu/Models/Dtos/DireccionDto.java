package niddu.Models.Dtos;
import lombok.Data;

@Data
public class DireccionDto {
    
    private String ciudad;
    private String colonia;
    private DepartamentoDto departamento;

}
