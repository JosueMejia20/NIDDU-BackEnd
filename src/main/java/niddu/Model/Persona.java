package com.niddu.model;

import jakarta.persistence.*;

@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_perona;

    private String nombres;
    private String email;

    // Getters y setters
    public Long getId() { return id_perona; }
    public void setId(Long id_perona) { this.id_perona = id_perona; }

    public String getNombres() { return nombres; }
    public void setNombre(String nombres) { this.nombres = nombres; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
