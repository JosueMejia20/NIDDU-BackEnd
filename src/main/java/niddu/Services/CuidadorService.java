package niddu.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import niddu.Models.Cuidador;
import niddu.Models.Direccion;
import niddu.Models.Persona;
import niddu.Repositories.CuidadorRepository;
import niddu.Repositories.DireccionRepository;
import niddu.Repositories.PersonaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuidadorService {

    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
private DireccionRepository direccionRepository;

        @Transactional
    public Cuidador registrarCuidador(Cuidador cuidador) {
        Persona persona = cuidador.getPersona();
        if (persona == null) {
        throw new IllegalArgumentException("Debe incluir información de la persona asociada al cuidador.");
        }
        Persona personaGuardada = personaRepository.save(persona);
        cuidador.setPersona(personaGuardada);
        Cuidador cuidadorGuardado = cuidadorRepository.save(cuidador);
        if (cuidador.getDirecciones() != null) {
            for (Direccion direccion : cuidador.getDirecciones()) {
                direccion.setCuidador(cuidadorGuardado);
                direccionRepository.save(direccion);
            }
        }
        return cuidadorGuardado;
    }

}
