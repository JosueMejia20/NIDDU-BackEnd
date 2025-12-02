package niddu.Services;

import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import niddu.Models.Cuidador;
import niddu.Models.CuidadorTipoServicio;
import niddu.Models.Departamento;
import niddu.Models.DetalleServicio;
import niddu.Models.Direccion;
import niddu.Models.Persona;
import niddu.Models.Servicio;
import niddu.Models.Dtos.CuidadorDto;
import niddu.Models.Dtos.CuidadorTipoServicioDto;
import niddu.Models.Dtos.DepartamentoDto;
import niddu.Models.Dtos.DireccionDto;
import niddu.Models.Dtos.PersonaDto;
import niddu.Models.Dtos.ServicioCompletoDto2;
import niddu.Repositories.CuidadorRepository;
import niddu.Repositories.CuidadorTipoServicioRepository;
import niddu.Repositories.DetalleServicioRepository;
import niddu.Repositories.DireccionRepository;
import niddu.Repositories.PersonaRepository;
import niddu.Repositories.ServicioRepository;

import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CuidadorService {

    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Autowired
    private PersonaRepository personaRepository;
    
    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private DetalleServicioRepository detalleServicioRepository;

    @Autowired
    private CuidadorTipoServicioRepository cuidadorTipoServicioRepository;

        @Transactional
    public Cuidador registrarCuidador(Cuidador cuidador) {
        Persona persona = cuidador.getPersona();
        if (persona == null) {
        throw new IllegalArgumentException("Debe incluir información de la persona asociada al cuidador.");
        }
        Persona personaGuardada = personaRepository.save(persona);
        cuidador.setPersona(personaGuardada);
        Cuidador cuidadorGuardado = cuidadorRepository.save(cuidador);
        if (cuidador.getDirecciones() != null) {
            for (Direccion direccion : cuidador.getDirecciones()) {
                direccion.setCuidador(cuidadorGuardado);
                direccionRepository.save(direccion);
            }
        }
        return cuidadorGuardado;
    }

    @Transactional
public CuidadorDto registrarYRetornarDTO(Cuidador cuidador) {
    Persona persona = cuidador.getPersona();
    if (persona == null) {
        throw new IllegalArgumentException("Debe incluir información de la persona asociada al cuidador.");
    }

    Persona personaGuardada = personaRepository.save(persona);
    cuidador.setPersona(personaGuardada);

    Cuidador cuidadorGuardado = cuidadorRepository.save(cuidador);

    return convertirACuidadorDto(cuidadorGuardado);
}

private CuidadorDto convertirACuidadorDto(Cuidador cuidador) {
    CuidadorDto dto = new CuidadorDto();
    dto.setIdCuidador(cuidador.getIdCuidador());
    dto.setCorreo(cuidador.getCorreo());
    dto.setFotoPerfil(cuidador.getFotoPerfil());
    dto.setFotoIdentidad(cuidador.getFotoIdentidad());
    dto.setPrecioxhora(cuidador.getPrecioxhora());
    dto.setExperiencia(cuidador.getExperiencia());
    dto.setIdEstadoCuidador(cuidador.getIdEstadoCuidador());

    // Persona
    Persona persona = cuidador.getPersona();
    if (persona != null) {
        PersonaDto personaDTO = new PersonaDto();
        personaDTO.setId(persona.getIdPersona());
        personaDTO.setNombres(persona.getNombres());
        personaDTO.setApellidos(persona.getApellidos());
        personaDTO.setTelefono(persona.getTelefono());
        dto.setPersona(personaDTO);
    }

    // Direcciones
    if (cuidador.getDirecciones() != null) {
        List<DireccionDto> direccionesDTO = cuidador.getDirecciones().stream().map(d -> {
            DireccionDto dirDTO = new DireccionDto();
            dirDTO.setCiudad(d.getCiudad());
            dirDTO.setColonia(d.getColonia());

            Departamento departamento = d.getDepartamento();
            if (departamento != null) {
                DepartamentoDto deptoDTO = new DepartamentoDto();
                deptoDTO.setIdDepartamento(departamento.getIdDepartamento());
                deptoDTO.setNombre(departamento.getNombreDepartamento());
                dirDTO.setDepartamento(deptoDTO);
            }

            return dirDTO;
        }).collect(Collectors.toList());

        dto.setDirecciones(direccionesDTO);
    }

    return dto;
}


public CuidadorDto obtenerCuidadorDtoPorId(int id) {
    Cuidador cuidador = cuidadorRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cuidador no encontrado con ID: " + id));
    return convertirACuidadorDto(cuidador);
}

    public CuidadorDto validarCredenciales(String correo, String contrasena) {

        Cuidador cuidador = cuidadorRepository.findByCorreoAndContrasena(correo, contrasena);

        if(cuidador == null) {

            return null;

        }

        return convertirACuidadorDto(cuidador);
    }
    
    public List<CuidadorTipoServicio> obtenerTodosCuidadorTipoServicios() {
        return cuidadorTipoServicioRepository.findAll();
    }

    public List<CuidadorTipoServicioDto> obtenerServiciosCuidadores() {
        List<CuidadorTipoServicio> cuidadoresServicios = cuidadorTipoServicioRepository.findAll();
        List<CuidadorTipoServicioDto> resultado = new ArrayList<>();

        // Usar un mapa para agrupar por cuidador
        Map<Integer, CuidadorTipoServicioDto> mapaCuidadores = new HashMap<>();

        for (CuidadorTipoServicio cts : cuidadoresServicios) {
            int idCuidador = cts.getIdCuidador(); // usando EmbeddedId

            CuidadorTipoServicioDto dto = mapaCuidadores.get(idCuidador);
            if (dto == null) {
                dto = new CuidadorTipoServicioDto();
                dto.setCuidador(cuidadorRepository.findById(idCuidador).orElse(null));
                dto.setTipoServicios(new ArrayList<>()); // inicializamos la lista
                mapaCuidadores.put(idCuidador, dto);
            }

            dto.getTipoServicios().add(cts.getTipoServicio());
        }

        resultado.addAll(mapaCuidadores.values());
        return resultado;
    }


    public CuidadorTipoServicioDto obtenerListaServiciosPorCuidadorId(int idCuidador) {

        List<CuidadorTipoServicioDto> cuidadorTipoServicioDtos = obtenerServiciosCuidadores();

        for (CuidadorTipoServicioDto cuidadorTipoServicioDto : cuidadorTipoServicioDtos) {
            
            if(cuidadorTipoServicioDto.getCuidador().getIdCuidador() == idCuidador) {
                return cuidadorTipoServicioDto;
            }

        }

        return null;
    }

    public boolean asignarTipoServicio(int idCuidador, int idTipoServicio) {

        CuidadorTipoServicio entity = new CuidadorTipoServicio();
        entity.setIdCuidador(idCuidador);
        entity.setIdTipoServicio(idTipoServicio);

        cuidadorTipoServicioRepository.save(entity);

        return true;
    }

    public List<ServicioCompletoDto2> obtenerDetalleDeTodosLosServicios(int idCuidador) {

        Cuidador cuidador = cuidadorRepository.findById(idCuidador).get();

        List<Servicio> servicios = servicioRepository.findAllByCuidador(cuidador);

        List<ServicioCompletoDto2> dtoList = new ArrayList<>();
        
        for (Servicio servicio : servicios) {
            ServicioCompletoDto2 servicioCompletoDto = new ServicioCompletoDto2();
            servicioCompletoDto.setNombreCuidador(servicio.getCuidador().getPersona().getNombres());
            servicioCompletoDto.setApellidoCuidador(servicio.getCuidador().getPersona().getApellidos());
            servicioCompletoDto.setNombreMascota(servicio.getMascota().getNombre());;
            servicioCompletoDto.setNombreServicio(servicio.getTipoServicio().getNombreServicio());

            DetalleServicio detalleServicio = detalleServicioRepository.findByServicio(servicio);

            servicioCompletoDto.setFecha(detalleServicio.getFecha());
            servicioCompletoDto.setSubtotal(detalleServicio.getSubtotal());
            servicioCompletoDto.setImpuesto(detalleServicio.getImpuesto());
            servicioCompletoDto.setTotal(detalleServicio.getTotal());
            servicioCompletoDto.setEstado(detalleServicio.getEstado());

            dtoList.add(servicioCompletoDto);
        }

        return dtoList;
    }



}
