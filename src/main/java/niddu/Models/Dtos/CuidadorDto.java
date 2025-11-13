package niddu.Models.Dtos;
import java.util.List;
import lombok.Data;
import niddu.Models.Dtos.PersonaDto;
import niddu.Models.Direccion;
import niddu.Models.Dtos.DireccionDto;
@Data
public class CuidadorDto {

    private String correo;
    private String fotoPerfil;
    private String fotoIdentidad;
    private int precioxhora;
    private int experiencia;
    private int idEstadoCuidador;
    private List<DireccionDto> direcciones;
    private PersonaDto persona;
    
}
