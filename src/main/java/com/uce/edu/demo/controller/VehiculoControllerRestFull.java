package com.uce.edu.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uce.edu.demo.modelo.Vehiculo;
import com.uce.edu.demo.service.IGestorEmpleadoService;
import com.uce.edu.demo.service.IVehiculoService;
import com.uce.edu.demo.service.to.VehiculoTo;

@RestController
@RequestMapping(path = "/vehiculos")
public class VehiculoControllerRestFull {

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IGestorEmpleadoService gestorEmpleadoService;

	// VEHICULO

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VehiculoTo>> consultarVehiculos() {
		List<VehiculoTo> vto = this.vehiculoService.buscarTodos();
		return ResponseEntity.status(HttpStatus.OK).body(vto);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void guardarVehiculo(@RequestBody Vehiculo vehiculo) {
		this.vehiculoService.insertar(vehiculo);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehiculo> buscarVehiculoId(@PathVariable int id) {
		Vehiculo v = this.vehiculoService.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(v);
	}

	@GetMapping(path = "/marca", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VehiculoTo>> consultarVehiculosPorMarca(@RequestParam String marca) {
		List<VehiculoTo> vls = this.vehiculoService.buscarPorMarcaTo(marca);
		for (VehiculoTo vehiculo : vls) {
			Link link = linkTo(methodOn(VehiculoControllerRestFull.class).buscarVehiculoId(vehiculo.getId()))
					.withRel("Vehiculos Marca");
			vehiculo.add(link);
		}
		return ResponseEntity.status(HttpStatus.OK).body(vls);
	}

	@DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void eliminarVehiculo(@PathVariable int id) {
		this.vehiculoService.eliminar(id);
	}

	// Retirar Vehiculo en Reserva
	@GetMapping(path = "/{reserva}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String buscarVehiculoReservado(@PathVariable String reserva) {
		boolean check = this.gestorEmpleadoService.retirarVehiculoReservado(reserva);
		return ResponseEntity.status(HttpStatus.OK).body(check).toString();
	}
}
