package niddu.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import niddu.Models.Cuidador;
import niddu.Models.Direccion;
import niddu.Models.Persona;
import niddu.Models.Dtos.CuidadorDto;
import niddu.Models.Dtos.PersonaDto;
import niddu.Repositories.CuidadorRepository;
import niddu.Repositories.DireccionRepository;
import niddu.Repositories.PersonaRepository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

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

    @Transactional
public CuidadorDto registrarYRetornarDTO(Cuidador cuidador) {
    Persona persona = cuidador.getPersona();
    if (persona == null) {
        throw new IllegalArgumentException("Debe incluir información de la persona asociada al cuidador.");
    }

    Persona personaGuardada = personaRepository.save(persona);
    cuidador.setPersona(personaGuardada);

    Cuidador cuidadorGuardado = cuidadorRepository.save(cuidador);

    return convertirACuidadorDto(cuidadorGuardado);
}

private CuidadorDto convertirACuidadorDto(Cuidador cuidador) {
    CuidadorDto dto = new CuidadorDto();
    dto.setCorreo(cuidador.getCorreo());
    dto.setFotoPerfil(cuidador.getFotoPerfil());
    dto.setFotoIdentidad(cuidador.getFotoIdentidad());
    dto.setPrecioxhora(cuidador.getPrecioxhora());
    dto.setExperiencia(cuidador.getExperiencia());
    dto.setIdEstadoCuidador(cuidador.getIdEstadoCuidador());

    Persona persona = cuidador.getPersona();
    if (persona != null) {
        PersonaDto personaDTO = new PersonaDto();
        personaDTO.setNombres(persona.getNombres());
        personaDTO.setApellidos(persona.getApellidos());
        personaDTO.setTelefono(persona.getTelefono());
        dto.setPersona(personaDTO);
    }

    return dto;
}

public CuidadorDto obtenerCuidadorDtoPorId(int id) {
    Cuidador cuidador = cuidadorRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cuidador no encontrado con ID: " + id));
    return convertirACuidadorDto(cuidador);
}


}
