package niddu.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import niddu.Models.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
    
}
