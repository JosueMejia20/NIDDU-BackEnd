package niddu.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import niddu.Models.Mascota;
import niddu.Repositories.MascotaRepository;

@Service
public class MascotaService {
    
    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaRepository.findAll();
    }

}
