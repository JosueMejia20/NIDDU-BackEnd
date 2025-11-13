package niddu.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import niddu.Models.CuidadorTipoServicio;
import niddu.Repositories.CuidadorTipoServicioRepository;

@Service
public class TipoServicioService {

    @Autowired
    private CuidadorTipoServicioRepository cuidadorTipoServicioRepository;

    public void registrarTiposDeServicio(int idCuidador, List<Integer> idTipoServicioList) {
    for (Integer idTipo : idTipoServicioList) {
        CuidadorTipoServicio relacion = new CuidadorTipoServicio();
        relacion.setIdCuidador(idCuidador);
        relacion.setIdTipoServicio(idTipo);
        cuidadorTipoServicioRepository.save(relacion);
    }
}
}
