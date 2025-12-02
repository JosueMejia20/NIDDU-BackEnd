package niddu.Models.Dtos;

import lombok.Data;
import niddu.Models.Mascota;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ServicioCompletoDto3 {
    private String nombreUsuario;
    private String apellidoUsuario;
    private String telefonoUsuario;
    private Mascota mascota;
    private String nombreServicio;

    private LocalDate fecha;
    private BigDecimal subtotal;
    private BigDecimal impuesto;
    private BigDecimal total;
    private String estado;
}