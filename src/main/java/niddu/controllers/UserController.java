package niddu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import niddu.Services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import niddu.Models.Usuario;
import niddu.Models.Dtos.UserDto;




@RestController
@RequestMapping("/usuarios")
public class UserController {
    
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "https://proud-beach-0a296230f.3.azurestaticapps.net:3000")
    @GetMapping("existeUsuario/{id}")
    public String existeUsuario(@PathVariable(name = "id") int id) {
        if (userService.existsById(id)) {
            return "El usuario existe en la base de datos.";
        }

        return "No existe un usuario con ese ID.";
    }

    @CrossOrigin(origins = "https://proud-beach-0a296230f.3.azurestaticapps.net:3000")
    @GetMapping("obtenerUsuario/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable(name = "id") int id) {
        return userService.getUserById(id);
    }

 @CrossOrigin(origins="https://proud-beach-0a296230f.3.azurestaticapps.net:3000")
 @GetMapping("validarCredenciales/{correo}/{password}")
    public UserDto validarCredenciales(@PathVariable(name = "correo") String correo, 
                                   @PathVariable(name = "password") String password) {

    return userService.getUserByCorreoAndPassword(correo, password);
    }

    @CrossOrigin(origins = "https://proud-beach-0a296230f.3.azurestaticapps.net:3000")
    @PostMapping("/registrar")
    public String registrarUsuario(@RequestBody Usuario usuario) {
        try {
        userService.guardarUsuario(usuario);
        return "Usuario registrado correctamente.";
        } catch (Exception e) {
        e.printStackTrace();
        return "Error al registrar el usuario: " + e.getMessage();
            }
    }

}
