package niddu.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import niddu.Model.Cliente;
import niddu.Model.Dtos.ClienteDto;
import niddu.Model.Usuario;
import niddu.Repository.ClienteRepository;
import niddu.Repository.UserRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Convierte un cliente a DTO
     */
    public ClienteDto doClienteDto(Cliente cliente) {
        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setIdUsuario(cliente.getUsuario().getId());
        dto.setFechaRegistro(
            cliente.getFechaRegistro() != null ? cliente.getFechaRegistro().toString() : null
        );
        return dto;
    }

    /**
     * Guarda un cliente a partir de su DTO
     */
    public void guardarClienteDesdeDto(ClienteDto dto) {
        Usuario usuario = userRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));

        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        clienteRepository.save(cliente);
    }

    /**
     * Obtiene un cliente por su ID
     */
    public ClienteDto getClienteById(int id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(this::doClienteDto).orElse(null);
    }

    /**
     * Elimina un cliente por su ID
     */
    public void eliminarCliente(int id) {
        clienteRepository.deleteById(id);
    }
}
