package niddu.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import niddu.Models.DetalleServicio;
import niddu.Models.Servicio;

import java.util.List;

public interface DetalleServicioRepository extends JpaRepository<DetalleServicio, Integer> {

    public DetalleServicio findByServicioUno(Servicio servicio);
    List<DetalleServicio> findByServicio(Servicio servicio);

}
