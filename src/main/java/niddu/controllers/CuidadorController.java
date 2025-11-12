package niddu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import niddu.Models.Cuidador;
import niddu.Services.CuidadorService;

@RestController
@RequestMapping("/cuidadores")
@CrossOrigin(origins = "http://localhost:3000")
public class CuidadorController {

    @Autowired
    private CuidadorService cuidadorService;

    @PostMapping("/registrar")
    public String registrarCuidador(@RequestBody Cuidador cuidador) {
        try {
            cuidadorService.registrarCuidador(cuidador);
            return "Cuidador registrado correctamente.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al registrar el cuidador: " + e.getMessage();
        }
    }
}
