package niddu.Models.Dtos;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReservaDto {
    private int idServicio;
    private int idUsuario;
    private int idMascota;
    private int idTipoServicio;

    private LocalDate fecha;
    private BigDecimal subtotal;
    private BigDecimal impuesto;
    private BigDecimal total;
    private String estado;
}
