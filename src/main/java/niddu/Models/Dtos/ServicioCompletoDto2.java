package niddu.Models.Dtos;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ServicioCompletoDto2 {
    private String nombreCuidador;
    private String apellidoCuidador;
    private String nombreMascota;
    private String nombreServicio;

    private LocalDate fecha;
    private BigDecimal subtotal;
    private BigDecimal impuesto;
    private BigDecimal total;
    private String estado;
}