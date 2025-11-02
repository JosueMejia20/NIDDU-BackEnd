package niddu.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import niddu.Model.Usuario;
import niddu.Model.Dtos.UserDto;
import niddu.Repository.UserRepository;

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
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setEstado(user.getEstado());

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
    public boolean validatedCredentials(String userName, String password) {
        Usuario user = userRepository.findByUserNameAndContrasena(userName, password).orElse(null);

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
    public UserDto getUserByUserNameAndPassword(String userName, String password) {
        Usuario user = userRepository.findByUserNameAndContrasena(userName, password).orElse(null);

        if (user != null) {
            return doUserDto(user);
        }

        return null;
    }
}
