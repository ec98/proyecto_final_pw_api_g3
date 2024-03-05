package com.uce.edu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uce.edu.demo.modelo.Cliente;
import com.uce.edu.demo.modelo.Vehiculo;
import com.uce.edu.demo.service.IClienteService;
import com.uce.edu.demo.service.IGestorEmpleadoService;

@RestController
@RequestMapping(path = "/empleados")
@CrossOrigin
public class EmpleadoControllerRestful {
	@Autowired
	private IGestorEmpleadoService gestorEmpleadoService;
	
	@Autowired
	private IClienteService clienteService;

	@PostMapping(path = "/guardarCliente", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void registrarCliente(@RequestBody Cliente cliente) {
		this.gestorEmpleadoService.registrarCliente(cliente);
	}
	
	@PutMapping(path = "/buscarCliente", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCliente(@RequestBody Cliente cliente) {
		this.clienteService.actualizar(cliente);
	}
	
	@PostMapping(path="/ingresarVehiculo", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void ingresarVehiculo(@RequestBody Vehiculo vehiculo) {
		this.gestorEmpleadoService.ingresarVehiculo(vehiculo);
	}
	
}
