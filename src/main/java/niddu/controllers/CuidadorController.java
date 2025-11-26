package niddu.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;
import niddu.Models.Cuidador;
import niddu.Models.CuidadorTipoServicio;
import niddu.Models.Dtos.CuidadorDto;
import niddu.Models.Dtos.CuidadorTipoServicioDto;
import niddu.Services.CuidadorService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/cuidadores")
@CrossOrigin(origins = "https://proud-beach-0a296230f.3.azurestaticapps.net:3000")
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

    @CrossOrigin(origins="https://proud-beach-0a296230f.3.azurestaticapps.net:3000")
    @GetMapping("validarCredenciales/{correo}/{password}")
    public CuidadorDto validarCredenciales(@PathVariable(name = "correo") String correo, 
                                   @PathVariable(name = "password") String password) {

    return cuidadorService.validarCredenciales(correo, password);
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

    @GetMapping("obtenerServiciosPorCuidadores")
    public List<CuidadorTipoServicioDto> obtenerServiciosCuidadores() {
        return cuidadorService.obtenerServiciosCuidadores();
    }

    @GetMapping("obtenerTodo")
    public List<CuidadorTipoServicio> obtenerTodo() {
        return cuidadorService.obtenerTodosCuidadorTipoServicios();
    }
    
    

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }

}
