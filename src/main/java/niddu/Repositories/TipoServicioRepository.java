package niddu.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import niddu.Models.TipoServicio;

@Repository
public interface TipoServicioRepository extends JpaRepository<TipoServicio, Integer> {

    public TipoServicio findByIdTipoServicio(int tipoServicio);

}
