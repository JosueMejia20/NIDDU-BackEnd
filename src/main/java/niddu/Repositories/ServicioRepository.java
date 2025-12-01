package niddu.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import niddu.Models.DetalleServicio;
import niddu.Models.Servicio;
import niddu.Models.TipoServicio;
import niddu.Models.Usuario;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

    public List<Servicio> findServicioByIdTipoServicio(TipoServicio idTipoServicio);

    public List<Servicio> findAllByUsuario(Usuario usuario);

    public List<Servicio> findByCuidadorId(int idCuidador);
    
    public List<DetalleServicio> findByServicio(Servicio servicio);

}
