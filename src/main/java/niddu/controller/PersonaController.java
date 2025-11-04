package niddu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import niddu.Services.PersonaService;
import niddu.Model.Persona;
import niddu.Model.Dtos.PersonaDto;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/registrar")
    public String registrarPersona(@RequestBody Persona persona) {
        try {
            personaService.guardarPersona(persona);
            return "Persona registrada correctamente.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al registrar la persona: " + e.getMessage();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/obtenerPersona/{id}")
    public PersonaDto obtenerPersona(@PathVariable int id) {
        return personaService.getPersonaById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/existeEmail/{email}")
    public String existePersonaPorEmail(@PathVariable String email) {
        return personaService.existsByEmail(email)
                ? "Ya existe una persona con ese correo."
                : "El correo está disponible.";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable int id) {
        try {
            personaService.eliminarPersona(id);
            return "Persona eliminada correctamente.";
        } catch (Exception e) {
            return "Error al eliminar la persona: " + e.getMessage();
        }
    }
}
