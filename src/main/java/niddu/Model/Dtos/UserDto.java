package niddu.Model.Dtos;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String correo;
    private int idTipoUsuario;
    private int idEstadoUsuario;
}
