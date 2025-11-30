package niddu.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import niddu.Models.CuidadorTipoServicio;
import niddu.Models.CuidadorTipoServicioId;

@Repository
public interface CuidadorTipoServicioRepository extends JpaRepository<CuidadorTipoServicio, CuidadorTipoServicioId> {

}
