package niddu.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import niddu.Models.TipoServicio;
import niddu.Services.ServicioService;

@CrossOrigin(origins = "https://proud-beach-0a296230f.3.azurestaticapps.net:3000")
@RestController
@RequestMapping(path = "tipoServicios")
public class TipoServicioController {

    @Autowired
    private ServicioService servicioService;
    

    @GetMapping(path = "obtenerLosTiposServicios")
    public List<TipoServicio> obtenerTodosTiposServicios() {
        return servicioService.obtenerTodosTipoServicios();
    }

}
