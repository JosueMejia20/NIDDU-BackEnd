package niddu.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import niddu.Models.Dtos.ServicioCompletoDto;
import niddu.Services.ServicioService;

@CrossOrigin(origins = "https://proud-beach-0a296230f.3.azurestaticapps.net:3000")
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

    @GetMapping("filtrarPorTipoServicio/{idTipoServicio}")
    public List<ServicioCompletoDto> filtrarServiciosPorTipo(@PathVariable(name = "idTipoServicio") int idTipoServicio) {
        return servicioService.obtenerServiciosPorIdTipoServicio(idTipoServicio);
    }

    @DeleteMapping("/RemoverTipoServicio/{idCuidador}/{idTipoServicio}")
    public String removerTipoServicioDeCuidador(@PathVariable(name = "idCuidador") int idCuidador, @PathVariable(name = "idTipoServicio") int idTipoServicio) {
        if (servicioService.removerTipoServicoDeCuidador(idCuidador, idTipoServicio)) {
            return "Tipo de servicio removido del cuidador correctamente.";
        }

        return "Error al remover el tipo de servicio del cuidador.";
    }
    
}
