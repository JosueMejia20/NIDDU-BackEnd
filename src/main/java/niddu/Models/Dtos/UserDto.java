package niddu.Models.Dtos;

import lombok.Data;

@Data
public class UserDto {

    private int id;

    private String Name;
    
    private String correo;

    private String estadoUsuario;

    private String fechaCreacion;
}
