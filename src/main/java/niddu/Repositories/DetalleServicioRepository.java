package niddu.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import niddu.Models.DetalleServicio;
import niddu.Models.Servicio;

public interface DetalleServicioRepository extends JpaRepository<DetalleServicio, Integer> {

    public DetalleServicio findByServicio(Servicio servicio);
}
