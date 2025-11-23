package niddu.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import niddu.Models.*;
import niddu.Models.Dtos.ServicioCompletoDto;
import niddu.Repositories.*;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private DetalleServicioRepository detalleServicioRepository;

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    public void registrarServicioCompleto(ServicioCompletoDto dto) {

        Servicio servicio = new Servicio();
        servicio.setUsuario(usuarioRepository.findById(dto.getIdUsuario()).orElseThrow());
        servicio.setCuidador(cuidadorRepository.findById(dto.getIdCuidador()).orElseThrow());
        servicio.setMascota(mascotaRepository.findById(dto.getIdMascota()).orElseThrow());
        Servicio servicioGuardado = servicioRepository.save(servicio);
        DetalleServicio detalle = new DetalleServicio();
        detalle.setServicio(servicioGuardado);
        detalle.setFecha(dto.getFecha());
        detalle.setSubtotal(dto.getSubtotal());
        detalle.setImpuesto(dto.getImpuesto());
        detalle.setTotal(dto.getTotal());
        detalle.setEstado(dto.getEstado());
        detalleServicioRepository.save(detalle);
    }
}
