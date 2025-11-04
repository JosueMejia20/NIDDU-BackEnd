package niddu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import niddu.Model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    boolean existsByEmail(String email);
}
