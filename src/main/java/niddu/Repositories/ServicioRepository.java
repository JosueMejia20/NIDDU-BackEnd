package niddu.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import niddu.Models.Servicio;
import niddu.Models.TipoServicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

    public List<Servicio> findServicioByIdTipoServicio(TipoServicio idTipoServicio);

}
