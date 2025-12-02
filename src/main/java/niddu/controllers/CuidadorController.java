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
import niddu.Models.Dtos.ServicioCompletoDto3;
import niddu.Services.CuidadorService;
import niddu.Services.ServicioService;

@RestController
@RequestMapping("/cuidadores")
@CrossOrigin(origins = "https://proud-beach-0a296230f.3.azurestaticapps.net:3000")
public class CuidadorController {

    @Autowired
    private CuidadorService cuidadorService;

    @Autowired
    private ServicioService servicioService;

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
    

    @GetMapping(path = "obtenerListaServiciosPorCuidadorId/{idCuidador}")
    public CuidadorTipoServicioDto obtenerListaServiciosPorCuidadorId(@PathVariable(name = "idCuidador") int idCuidador){
        return cuidadorService.obtenerListaServiciosPorCuidadorId(idCuidador);
    }

    @PostMapping(path = "asignarTipoServicio/{idCuidador}/{idTipoServicio}")
    public String asignarTipoServicio(@PathVariable(name = "idCuidador") int idCuidador, @PathVariable(name = "idTipoServicio") int idTipoServicio) {

        if(cuidadorService.asignarTipoServicio(idCuidador, idTipoServicio))
            return "TipoServicio se asigno correctamente";
        
        return "Error al asignar el tipo de servicio";

    }

    @GetMapping(path = "/obtenerHistorialReservas/{idCuidador}")
    public List<ServicioCompletoDto3> obtenerHistorialDeReservas(@PathVariable(name = "idCuidador") int idCuidador) {
        return cuidadorService.obtenerDetalleDeTodosLosServicios(idCuidador);
    }

    @PutMapping("actualizarEstadoReserva/{idServicio}/{nuevoEstado}")
    public String actualizarEstadoReserva(@PathVariable(name = "idServicio") int idServicio, @PathVariable(name = "nuevoEstado") int nuevoEstado) {
        if(servicioService.cambiarEstadoReserva(idServicio, nuevoEstado))
            return "El estado de la reserva se actualizo correctamente.";

        return "Error al actualizar el estado de la reserva.";
    }
    

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }

}
