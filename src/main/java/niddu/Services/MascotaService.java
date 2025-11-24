package niddu.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import niddu.Models.Mascota;
import niddu.Models.Usuario;
import niddu.Repositories.MascotaRepository;

@Service
public class MascotaService {
    
    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UserService usuarioService;

    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaRepository.findAll();
    }

    public List<Mascota> obtenerMascotasPorUsuario(int idUsuario) {
        return mascotaRepository.findByUsuarioId(idUsuario);
    }

    public boolean agregarMascota(Mascota nvaMascota, int idUsuario) {
        Usuario usuarioActual = usuarioService.getUserById(idUsuario);
        List<Mascota> mascotas = mascotaRepository.findByUsuarioId(idUsuario);

        for (Mascota mascota : mascotas) {
            if(nvaMascota.getNombre().equals(mascota.getNombre())) {
                return false;
            }
        }

        nvaMascota.setUsuario(usuarioActual);

        mascotaRepository.save(nvaMascota);

        return true;

    }

}
