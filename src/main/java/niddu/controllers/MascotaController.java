package niddu.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import niddu.Models.Mascota;
import niddu.Services.MascotaService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;
    
    @GetMapping("/todas")
    public List<Mascota> obtenerTodasLasMascotas() {

        return mascotaService.obtenerTodasLasMascotas();
        
    }

    @GetMapping("/usuarioMascotas/{idUsuario}")
    public List<Mascota> obtenerMascotasDeUsuario(@PathVariable(name = "idUsuario") int idUsuario) {
        return mascotaService.obtenerMascotasPorUsuario(idUsuario);
    }
    
}