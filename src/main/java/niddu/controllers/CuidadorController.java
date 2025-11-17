package niddu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;
import niddu.Models.Cuidador;
import niddu.Models.Dtos.CuidadorDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<CuidadorDto> obtenerCuidadorPorId(@PathVariable int id) {
        try {
            CuidadorDto dto = cuidadorService.obtenerCuidadorDtoPorId(id);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }

}
