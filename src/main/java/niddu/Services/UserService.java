package niddu.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import niddu.Models.Usuario;
import niddu.Models.Dtos.UserDto;
import niddu.Repositories.UserRepository;

@Service
public class UserService{
    
    @Autowired
    private UserRepository userRepository;

    /**
     * Convierte un Usuario a UserDto.
     * @param user el usuario a convertir.
     * @return el UserDto resultante.
     * @author Mauricio Velásquez
     */
    public UserDto doUserDto(Usuario user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getIdUsuario());
        userDto.setName(user.getPersona().getNombres() + " " + user.getPersona().getApellidos());
        userDto.setCorreo(user.getCorreo());
        userDto.setEstadoUsuario(user.getEstadoUsuario().getNombreEstado());
        userDto.setFechaCreacion(user.getFechaRegistro().toString());

        return userDto;
    }


    /**
     * Verifica si el usuario con el id dado existe.
     * @param id id del usuario.
     * @return true si lo encuentra, de lo contrario false.
     * @author Mauricio Velásquez
     */
    public boolean existsById(int id) {
        return userRepository.existsById(id);
    }


    /**
     * Obtiene un usuario por su ID.
     * @param id id del usuario.
     * @return al usuario si lo encuentra, de lo contrario retorna null.
     * @author Mauricio Velásquez
     */
    public UserDto getUserById(int id) {

        UserDto userDto = new UserDto();

        if(existsById(id)) {
            Usuario user = userRepository.findById(id).orElse(null);
            userDto = doUserDto(user);

            return userDto;
        }

        return null;
    }

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
    }

}
