package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.IGestorClienteService;
import com.uce.edu.demo.service.IGestorReporteService;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteControllerRestFull {

	@Autowired
	private IGestorReporteService gestorReporteService;

	@Autowired
	private IGestorClienteService gestorClienteService;

	@Autowired
	private IClienteService clienteService;

	// CLIENTE

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCliente(@RequestBody Cliente cliente) {
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

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> buscarClientePorApellido(@RequestParam String apellido) {
		List<Cliente> cApe = this.clienteService.buscarPorApellido(apellido);
		return ResponseEntity.status(HttpStatus.OK).body(cApe);
	}

}
