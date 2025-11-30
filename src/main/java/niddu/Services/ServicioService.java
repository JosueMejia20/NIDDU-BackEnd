package niddu.Services;

import java.util.List;
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

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    public ServicioCompletoDto hecerDtoServicioCompleto(Servicio servicio) {
        
        ServicioCompletoDto dto = new ServicioCompletoDto();

        dto.setIdServicio(servicio.getIdServicios());
        dto.setIdUsuario(servicio.getUsuario().getId());
        dto.setIdCuidador(servicio.getCuidador().getIdCuidador());
        dto.setIdMascota(servicio.getMascota().getIdMascota());

        return dto;

    }

    public void registrarServicioCompleto(ServicioCompletoDto dto) {

        Servicio servicio = new Servicio();
        servicio.setUsuario(usuarioRepository.findById(dto.getIdUsuario()).orElseThrow());
        servicio.setCuidador(cuidadorRepository.findById(dto.getIdCuidador()).orElseThrow());
        servicio.setMascota(mascotaRepository.findById(dto.getIdMascota()).orElseThrow());
        servicio.setTipoServicio(tipoServicioRepository.findById(dto.getIdTipoServicio()).orElseThrow());
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

    public List<ServicioCompletoDto> obtenerServiciosPorIdTipoServicio(int idTipoServicio) {

        TipoServicio tipoServicio = tipoServicioRepository.findByIdTipoServicio(idTipoServicio);

        List<Servicio> listaServicios = servicioRepository.findServicioByIdTipoServicio(tipoServicio);

        List<ServicioCompletoDto> listaDto = new java.util.ArrayList<>();

        ServicioCompletoDto dto = new ServicioCompletoDto();

        for (Servicio servicio : listaServicios) {
            dto = hecerDtoServicioCompleto(servicio);
            listaDto.add(dto);
        }

        return listaDto;

    }

    public List<TipoServicio> obtenerTodosTipoServicios() {
        return tipoServicioRepository.findAll();
    }
}
