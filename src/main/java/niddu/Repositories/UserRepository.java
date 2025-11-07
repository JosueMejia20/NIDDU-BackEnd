package niddu.Repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import niddu.Models.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreoAndContrasena(String correo, String contrasena);
<<<<<<< HEAD:src/main/java/niddu/Repositories/UserRepository.java
    boolean existsByCorreo(String correo);
=======
>>>>>>> 3a6e192 (EndPoint de Usuarios actualizado):src/main/java/niddu/Repository/UserRepository.java
}
