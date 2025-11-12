package niddu.Models.Dtos;

import lombok.Data;
import niddu.Models.Direccion;

@Data
public class UserDto {
    private int id;
    private String correo;
    private String nombre;
    private String apellido;
    private String telefono;
    private Direccion direccion;
    private int idTipoUsuario;
    private int idEstadoUsuario;
    private String fechaCreacion;
}
