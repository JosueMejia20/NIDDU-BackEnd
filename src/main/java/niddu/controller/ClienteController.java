package niddu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import niddu.Services.ClienteService;
import niddu.Model.Dtos.ClienteDto;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/registrar")
    public String registrarCliente(@RequestBody ClienteDto clienteDto) {
        try {
            clienteService.guardarClienteDesdeDto(clienteDto);
            return "Cliente registrado correctamente.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al registrar el cliente: " + e.getMessage();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/obtenerCliente/{id}")
    public ClienteDto obtenerCliente(@PathVariable int id) {
        return clienteService.getClienteById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable int id) {
        try {
            clienteService.eliminarCliente(id);
            return "Cliente eliminado correctamente.";
        } catch (Exception e) {
            return "Error al eliminar el cliente: " + e.getMessage();
        }
    }
}
