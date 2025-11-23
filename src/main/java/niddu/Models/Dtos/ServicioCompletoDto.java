package niddu.Models.Dtos;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ServicioCompletoDto {
    private int idUsuario;
    private int idCuidador;
    private int idMascota;

    private LocalDate fecha;
    private BigDecimal subtotal;
    private BigDecimal impuesto;
    private BigDecimal total;
    private String estado;
}
