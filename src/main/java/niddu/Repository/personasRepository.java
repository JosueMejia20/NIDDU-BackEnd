package com.niddu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.niddu.model.personas;

public interface personasRepository extends JpaRepository<personas, Long> {
}

