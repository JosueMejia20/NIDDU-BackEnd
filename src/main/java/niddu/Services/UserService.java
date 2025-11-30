package niddu.Services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import niddu.Models.Usuario;
import niddu.Models.Dtos.ServicioCompletoDto;
import niddu.Models.Dtos.UserDto;
import niddu.Models.DetalleServicio;
import niddu.Models.Direccion;
import niddu.Models.Persona;
import niddu.Models.Servicio;
import niddu.Repositories.DetalleServicioRepository;
import niddu.Repositories.PersonaRepository;
import niddu.Repositories.ServicioRepository;
import niddu.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DetalleServicioRepository detalleServicioRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    public UserDto doUserDto(Usuario user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setCorreo(user.getCorreo());
        dto.setNombre(user.getPersona().getNombres());
        dto.setApellido(user.getPersona().getApellidos());
        dto.setTelefono(user.getPersona().getTelefono());
        dto.setDireccion(user.getDirecciones().isEmpty() ? null : user.getDirecciones().get(0));
        dto.setFechaCreacion(user.getFechaRegistro().toString());
        dto.setIdTipoUsuario(user.getIdTipoUsuario());
        dto.setIdEstadoUsuario(user.getIdEstadoUsuario());
        return dto;
    }

    public boolean existsById(int id) {
        return userRepository.existsById(id);
    }

    public Usuario getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public boolean validatedCredentials(String correo, String password) {
        Usuario user = userRepository.findByCorreoAndContrasena(correo, password).orElse(null);
        return user != null;
    }

    public UserDto getUserByCorreoAndPassword(String correo, String password) {
        return userRepository.findByCorreoAndContrasena(correo, password)
                .map(this::doUserDto)
                .orElse(null);
    }

   @Autowired
    private PersonaRepository personaRepository;

    public void guardarUsuario(Usuario usuario) {
        if (usuario.getPersona() != null) {
            Persona personaGuardada = personaRepository.save(usuario.getPersona());
            usuario.setPersona(personaGuardada);
        }

        if (usuario.getDirecciones() != null) {
            for (Direccion direccion : usuario.getDirecciones()) {
                direccion.setUsuario(usuario);
            }
        }

        userRepository.save(usuario);
    }


    public List<ServicioCompletoDto> obtenerDetalleDeTodosLosServicios(int idUsuario) {

        List<Servicio> servicios = servicioRepository.findAllByUsuario(userRepository.findById(idUsuario).get());

        List<ServicioCompletoDto> dtoList = new ArrayList<>();
        
        for (Servicio servicio : servicios) {
            ServicioCompletoDto servicioCompletoDto = new ServicioCompletoDto();
            servicioCompletoDto.setIdServicio(servicio.getIdServicios());
            servicioCompletoDto.setIdUsuario(idUsuario);
            servicioCompletoDto.setIdCuidador(servicio.getCuidador().getIdCuidador());
            servicioCompletoDto.setIdMascota(servicio.getMascota().getIdMascota());
            servicioCompletoDto.setIdTipoServicio(servicio.getTipoServicio().getIdTipoServicio());

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
