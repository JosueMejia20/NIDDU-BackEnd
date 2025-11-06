package niddu.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import niddu.Models.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
