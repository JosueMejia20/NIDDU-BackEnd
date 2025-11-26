package niddu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import niddu.Models.Dtos.CuidadorTipoServicioDto;
import niddu.Services.TipoServicioService;

@RestController
@RequestMapping("/tiposervicio")
public class TipoServicioController {

    @Autowired
    private TipoServicioService tipoServicioService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarTiposDeServicio(@RequestBody CuidadorTipoServicioDto dto) {
    tipoServicioService.registrarTiposDeServicio(dto.getIdCuidador(), dto.getIdTipoServicios());
    return ResponseEntity.ok("Tipos de servicio registrados para cuidador.");
    }
 
}
