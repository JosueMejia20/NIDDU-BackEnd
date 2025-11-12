package niddu.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import niddu.Models.Usuario;
import niddu.Models.Direccion;
import niddu.Models.Persona;
import niddu.Model.Dtos.UserDto;
import niddu.Repositories.PersonaRepository;
import niddu.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto doUserDto(Usuario user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setCorreo(user.getCorreo());
        dto.setNombre(user.getPersona().getNombres());
        dto.setApellido(user.getPersona().getApellidos());
        dto.setTelefono(user.getPersona().getTelefono());
        dto.setDireccion(user.getDirecciones().isEmpty() ? null : user.getDirecciones().get(0));
        dto.setFechaCreacion(user.getFechaRegistro().toString());
        dto.setIdTipoUsuario(user.getIdTipoUsuario());
        dto.setIdEstadoUsuario(user.getIdEstadoUsuario());
        return dto;
    }

    public boolean existsById(int id) {
        return userRepository.existsById(id);
    }

    public UserDto getUserById(int id) {
        return userRepository.findById(id)
                .map(this::doUserDto)
                .orElse(null);
    }

    public boolean validatedCredentials(String correo, String password) {
        Usuario user = userRepository.findByCorreoAndContrasena(correo, password).orElse(null);
        return user != null;
    }

    public UserDto getUserByCorreoAndPassword(String correo, String password) {
        return userRepository.findByCorreoAndContrasena(correo, password)
                .map(this::doUserDto)
                .orElse(null);
    }

   @Autowired
    private PersonaRepository personaRepository;
    public void guardarUsuario(Usuario usuario) {
        if (usuario.getPersona() != null) {
            Persona personaGuardada = personaRepository.save(usuario.getPersona());
            usuario.setPersona(personaGuardada);
        }

        if (usuario.getDirecciones() != null) {
            for (Direccion direccion : usuario.getDirecciones()) {
                direccion.setUsuario(usuario);
            }
        }

        userRepository.save(usuario);
    }

}
