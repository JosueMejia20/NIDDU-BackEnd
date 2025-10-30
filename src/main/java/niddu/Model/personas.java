package com.niddu.model;

import jakarta.persistence.*;

@Entity
public class personas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_persona;

    private String nombres;
    private String email;

    // Getters y setters
    public Long getId() { return id_persona; }
    public void setId(Long id_persona) { this.id_persona = id_persona; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
