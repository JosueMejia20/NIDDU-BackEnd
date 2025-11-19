package niddu.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import niddu.Models.Mascota;
import niddu.Services.MascotaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;
    
    @CrossOrigin(origins = "https://proud-beach-0a296230f.3.azurestaticapps.net:3000")
    @GetMapping("/todas")
    public List<Mascota> obtenerTodasLasMascotas() {

        return mascotaService.obtenerTodasLasMascotas();
        
    }

    @CrossOrigin(origins = "https://proud-beach-0a296230f.3.azurestaticapps.net:3000")
    @GetMapping("/usuarioMascotas/{idUsuario}")
    public List<Mascota> obtenerMascotasDeUsuario(@PathVariable(name = "idUsuario") int idUsuario) {
        return mascotaService.obtenerMascotasPorUsuario(idUsuario);
    }

    @CrossOrigin(origins = "https://proud-beach-0a296230f.3.azurestaticapps.net:3000")
    @PostMapping("/agregarMascota/{idUsuario}")
    public String agregarMascota(@RequestBody Mascota nvaMascota, @PathVariable(name = "idUsuario") int idUsuario) {

        if(mascotaService.agregarMascota(nvaMascota, idUsuario)) {
            return "Mascota agregada correctamente.";
        }

        return "Ocurrio un error al agregar la mascota. Es posible que ya exista una mascota con ese nombre para este usuario.";
    }
    
    
}