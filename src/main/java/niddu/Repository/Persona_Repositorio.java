package com.niddu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.niddu.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
