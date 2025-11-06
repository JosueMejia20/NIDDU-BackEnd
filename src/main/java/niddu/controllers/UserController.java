package niddu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import niddu.Models.Usuario;
import niddu.Models.Dtos.UserDto;
import niddu.Services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("existeUsuario/{id}")
    public String existeUsuario(@PathVariable(name = "id") int id) {
        if (userService.existsById(id)) {
            return "El usuario existe en la base de datos.";
        }

        return "No existe un usuario con ese ID.";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("obtenerUsuario/{id}")
    public UserDto obtenerUsuarioPorId(@PathVariable(name = "id") int id) {
        return userService.getUserById(id);
    }

    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("validarCredenciales/{email}/{password}")
    public UserDto validarCredenciales(@PathVariable(name = "email") String email, 
                                       @PathVariable(name = "password") String password) {

        return userService.getUserByEmailAndPassword(email, password);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/registrar")
    public boolean registrarUsuario(@RequestBody Usuario usuario) {
        return userService.guardarUsuario(usuario);
    }

}
