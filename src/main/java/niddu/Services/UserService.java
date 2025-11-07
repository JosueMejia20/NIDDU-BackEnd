package niddu.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import niddu.Models.Usuario;
import niddu.Models.Dtos.UserDto;
import niddu.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto doUserDto(Usuario user) {
<<<<<<< HEAD
        UserDto userDto = new UserDto();

        userDto.setId(user.getIdUsuario());
        userDto.setName(user.getPersona().getNombres() + " " + user.getPersona().getApellidos());
        userDto.setCorreo(user.getCorreo());
        userDto.setEstadoUsuario(user.getEstadoUsuario().getNombreEstado());
        userDto.setFechaCreacion(user.getFechaRegistro().toString());

        return userDto;
=======
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setCorreo(user.getCorreo());
        dto.setIdTipoUsuario(user.getIdTipoUsuario());
        dto.setIdEstadoUsuario(user.getIdEstadoUsuario());
        return dto;
>>>>>>> 3a6e192 (EndPoint de Usuarios actualizado)
    }

    public boolean existsById(int id) {
        return userRepository.existsById(id);
    }

    public UserDto getUserById(int id) {
        return userRepository.findById(id)
                .map(this::doUserDto)
                .orElse(null);
    }

<<<<<<< HEAD
    /**
     * Valida las credenciales de un usuario.
     * @param userName Nombre de usuario.
     * @param password Contraseña del usuario.
     * @return true si las credenciales son válidas, false en caso contrario.
     * @author Mauricio Velásquez
     */
    public boolean validatedCredentials(String email, String password) {
        Usuario user = userRepository.findByCorreoAndContrasena(email, password).orElse(null);

        if(user != null) {
            return true;
        }

        return false;
    }

    /**
     * Valida las credenciales de un usuario.
     * @param userName Nombre de usuario.
     * @param password Contraseña del usuario.
     * @return el usuario encontrado, si no lo encuentra retorna null.
     * @author Mauricio Velásquez
     */
    public UserDto getUserByEmailAndPassword(String email, String password) {
        Usuario user = userRepository.findByCorreoAndContrasena(email, password).orElse(null);

        if (user != null) {
            return doUserDto(user);
        }

        return null;
    }

    /**
     * Valida las credenciales de un usuario.
     * @param nvoUsuario usuario.
     * @return true si el correo no existe y se guarda el usuario, false si el correo ya existe.
     * @author Mauricio Velásquez
     */
    public boolean guardarUsuario(Usuario nvoUsuario) {
        if(userRepository.existsByCorreo(nvoUsuario.getCorreo())) {
        return false;
    }

    if(nvoUsuario.getDirecciones() != null) {
        nvoUsuario.getDirecciones().forEach(d -> d.setUsuario(nvoUsuario));
    }

    userRepository.save(nvoUsuario);

    return true;
=======
    public boolean validatedCredentials(String correo, String password) {
        Usuario user = userRepository.findByCorreoAndContrasena(correo, password).orElse(null);
        return user != null;
    }

    public UserDto getUserByCorreoAndPassword(String correo, String password) {
        return userRepository.findByCorreoAndContrasena(correo, password)
                .map(this::doUserDto)
                .orElse(null);
    }

    public void guardarUsuario(Usuario usuario) {
        userRepository.save(usuario);
>>>>>>> 3a6e192 (EndPoint de Usuarios actualizado)
    }
}
