package niddu.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import niddu.Models.Cuidador;

@Repository
public interface CuidadorRepository extends JpaRepository<Cuidador, Integer> {
    public Cuidador findByCorreoAndContrasena(String correo, String contrasena);
}
