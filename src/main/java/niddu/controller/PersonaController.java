package com.niddu.controller;

import com.niddu.model.Persona;
import com.niddu.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping
    public List<Persona> listar() {
        return personaRepository.findAll();
    }
}
