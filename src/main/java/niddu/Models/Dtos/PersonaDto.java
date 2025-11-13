package niddu.Models.Dtos;

import lombok.Data;

@Data
public class PersonaDto {

    private int id;
    private String nombres;
    private String apellidos;
    private String telefono;
    private int idUsuario;
    private PersonaDto persona;
}
