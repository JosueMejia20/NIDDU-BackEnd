package niddu.controller;

import niddu.Model.Persona;
import niddu.Repository.PersonaRepository;
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
    @GetMapping("/ping")
    public String ping() {
        return "Hola Mundo";
    }
}
