package niddu.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import niddu.Models.TipoServicio;
import niddu.Repositories.TipoServicioRepository;

import java.util.List;

@Service
public class TipoServicioService {

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    public List<TipoServicio> listarTodos() {
        return tipoServicioRepository.findAll();
    }
}
