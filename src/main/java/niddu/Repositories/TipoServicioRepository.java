package niddu.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import niddu.Models.TipoServicio;

public interface TipoServicioRepository extends JpaRepository<TipoServicio, Integer> {

    public TipoServicio findByIdTipoServicio(int tipoServicio);

}
