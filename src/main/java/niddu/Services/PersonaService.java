package niddu.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import niddu.Repository.PersonaRepository;
import niddu.Model.Persona;
import niddu.Model.Dtos.PersonaDto;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    /**
     * Convierte una entidad Persona
     * @param persona la entidad a convertir
     * @return PersonaDto
     */
    public PersonaDto doPersonaDto(Persona persona) {
        PersonaDto dto = new PersonaDto();
        dto.setId(persona.getId());
        dto.setNombres(persona.getNombres());
        dto.setApellidos(persona.getApellidos());
        dto.setEmail(persona.getEmail());
        dto.setTelefono(persona.getTelefono());

        if (persona.getUsuario() != null) {
            dto.setIdUsuario(persona.getUsuario().getId());
        }

        return dto;
    }

    /**
     * Guarda una nueva persona
     * @param persona objeto Persona a guardar
     */
    public void guardarPersona(Persona persona) {
        personaRepository.save(persona);
    }

    /**
     * Obtiene una persona por su id
     * @param id identificador de la persona
     * @return PersonaDto o null si no existe
     */
    public PersonaDto getPersonaById(int id) {
        Optional<Persona> persona = personaRepository.findById(id);
        return persona.map(this::doPersonaDto).orElse(null);
    }

    /**
     * Verifica si ya existe una persona con el email dao
     * @param email correo a validar
     * @return true si ya existe, false si no
     */
    public boolean existsByEmail(String email) {
        return personaRepository.existsByEmail(email);
    }

    /**
     * Elimina una persona por su ID
     * @param id identificador de la persona
     */
    public void eliminarPersona(int id) {
        personaRepository.deleteById(id);
    }
}
