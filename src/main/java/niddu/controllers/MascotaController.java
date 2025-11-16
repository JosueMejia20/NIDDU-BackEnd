package niddu.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import niddu.Models.Mascota;
import niddu.Services.MascotaService;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;
    
    @GetMapping("/todas")
    public List<Mascota> obtenerTodasLasMascotas() {

        return mascotaService.obtenerTodasLasMascotas();
        
    }
}