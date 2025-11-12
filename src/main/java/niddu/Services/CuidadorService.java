package niddu.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import niddu.Models.Cuidador;
import niddu.Models.Persona;
import niddu.Repositories.CuidadorRepository;
import niddu.Repositories.PersonaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuidadorService {

    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Autowired
    private PersonaRepository personaRepository;
    
    @Transactional
    public Cuidador registrarCuidador(Cuidador cuidador) {
        Persona persona = cuidador.getPersona();

        if (persona == null) {
            throw new IllegalArgumentException("Debe incluir información de la persona asociada al cuidador.");
        }


        Persona personaGuardada = personaRepository.save(persona);

        cuidador.setPersona(personaGuardada);
        return cuidadorRepository.save(cuidador);
    }
}
