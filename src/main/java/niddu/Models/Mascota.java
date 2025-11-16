package niddu.Models;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private int idMascota;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "raza")
    private String raza;

    @Column(name = "edad")
    private BigDecimal edad;

    @Column(name = "peso")
    private BigDecimal peso;

    @Column(name = "alergias")
    private String alergias;

    @Column(name = "veterinario_preferencia")
    private String veterinarioPreferencia;

    @Column(name = "vacunas_al_dia")
    private boolean vacunasAlDia;

    @Lob
    @Column(name = "notas")
    private String notas;
    
}
