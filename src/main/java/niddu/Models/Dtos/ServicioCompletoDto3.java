package niddu.Models.Dtos;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ServicioCompletoDto3 {
    private int idServicio;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String telefonoUsuario;
    private String nombreMascota;
    private String tipoMascota;
    private String razaMascota;
    private BigDecimal edadMascota;
    private BigDecimal pesoMascota;
    private String alergias;
    private String veterinarioPreferencia;
    private boolean vacunasAlDia;
    private String notas;
    private String nombreServicio;

    private LocalDate fecha;
    private BigDecimal subtotal;
    private BigDecimal impuesto;
    private BigDecimal total;
    private String estado;
}