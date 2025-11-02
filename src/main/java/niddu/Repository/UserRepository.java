package niddu.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import niddu.Model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUserNameAndContrasena(String userName, String contrasena);
}
