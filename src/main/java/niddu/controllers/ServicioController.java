package niddu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import niddu.Models.Dtos.ServicioCompletoDto;
import niddu.Services.ServicioService;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarServicioCompleto(@RequestBody ServicioCompletoDto dto) {
        servicioService.registrarServicioCompleto(dto);
        return ResponseEntity.ok("Servicio y detalle registrados correctamente.");
    }
}
