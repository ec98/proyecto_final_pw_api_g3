package com.uce.edu.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uce.edu.demo.modelo.Cliente;
import com.uce.edu.demo.modelo.Reserva;
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.IGestorClienteService;

@RestController
@RequestMapping(path = "/clientes")
@CrossOrigin
public class ClienteControllerRestFull {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IGestorClienteService gestorClienteService;

	// CLIENTE
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCliente(@RequestBody Cliente cliente, @PathVariable int id) {
		cliente.setId(id);
		this.clienteService.actualizar(cliente);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void registrarCliente(@RequestBody Cliente cliente) {
		this.clienteService.insertar(cliente);
	}

	@DeleteMapping(path = "/{id}")
	public void eliminarCliente(@PathVariable int id) {
		this.clienteService.eliminar(id);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> buscarCliente(@PathVariable Integer id) {
//		System.out.println("El ID: " + id);
		Cliente cliente = this.clienteService.buscar(id);
//		modelo.addAttribute("cliente", cliente);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}

	@GetMapping(path = "/clienteApellido", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> buscarClientePorApellido(@RequestParam String apellido) {
		List<Cliente> cApe = this.clienteService.buscarPorApellido(apellido);
		return ResponseEntity.status(HttpStatus.OK).body(cApe);
	}

	@PostMapping(path = "/reserva/guardarCliente", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void guardarClienteReserva(@RequestBody Cliente cliente) {
		this.gestorClienteService.registrarCliente(cliente);
	}

	@PutMapping(path = "/reserva/{id}/actualizarCliente", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarClienteReserva(@RequestBody Cliente cliente, @PathVariable int id) {
		cliente.setId(id);
		this.gestorClienteService.actualizarCliente(cliente);
	}

	@PostMapping(path = "/calcular/{numeroTargeta}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reserva> obtainValores(@RequestBody Reserva reserva, @PathVariable String numeroTargeta,
			@RequestParam LocalDateTime fechaInicio, @RequestParam LocalDateTime fechaFin) {

		Reserva r = this.gestorClienteService.calcularValores(numeroTargeta, reserva.getVehiculo(),
				reserva.getCliente(), fechaInicio, fechaFin);
		return ResponseEntity.status(HttpStatus.OK).body(r);
	}

	// Buscar Por Cedula, luego uso el actualizar normal
	@GetMapping(path = "/clienteCedula/{cedula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> buscarClientePorCedula(@PathVariable String cedula) {
		Cliente cApe = this.clienteService.buscarPorCedula(cedula);
		return ResponseEntity.status(HttpStatus.OK).body(cApe);
	}

}
