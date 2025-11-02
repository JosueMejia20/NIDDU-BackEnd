package niddu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import niddu.Model.Dtos.UserDto;
import niddu.Services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("existeUsuario/{id}")
    public String existeUsuario(@PathVariable(name = "id") int id) {
        if (userService.existsById(id)) {
            return "El usuario existe en la base de datos.";
        }

        return "No existe un usuario con ese ID.";
    }

    @GetMapping("obtenerUsuario/{id}")
    public UserDto obtenerUsuarioPorId(@PathVariable(name = "id") int id) {
        return userService.getUserById(id);
    }

    @GetMapping("validarCredenciales/{userName}/{password}")
    public UserDto validarCredenciales(@PathVariable(name = "userName") String userName, 
                                       @PathVariable(name = "password") String password) {

        return userService.getUserByUserNameAndPassword(userName, password);
    }
}
