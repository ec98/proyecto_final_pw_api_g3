package com.uce.edu.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
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

import com.uce.edu.demo.modelo.Reserva;
import com.uce.edu.demo.modelo.Vehiculo;
import com.uce.edu.demo.service.IGestorClienteService;
import com.uce.edu.demo.service.IGestorEmpleadoService;
import com.uce.edu.demo.service.IVehiculoService;
import com.uce.edu.demo.service.to.VehiculoTo;

@RestController
@RequestMapping(path = "/vehiculos")
@CrossOrigin
public class VehiculoControllerRestFull {

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IGestorEmpleadoService gestorEmpleadoService;

	@Autowired
	private IGestorClienteService gestorClienteService;

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
	public ResponseEntity<VehiculoTo> buscarVehiculoId(@PathVariable int id) {
		VehiculoTo v = this.vehiculoService.buscarId(id);

		Link link = linkTo(methodOn(VehiculoControllerRestFull.class).consultarVehiculosPorMarca(v.getMarca()))
				.withRel("Id Marca Vehiculo");
		v.add(link);

		return ResponseEntity.status(HttpStatus.OK).body(v);
	}

	@GetMapping(path = "/buscarVehiculo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Vehiculo>> consultarVehiculosPorMarca(@RequestParam String marca) {
		List<Vehiculo> vls = this.vehiculoService.buscarPorMarca(marca);
		return ResponseEntity.status(HttpStatus.OK).body(vls);
	}

	@DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void eliminarVehiculo(@PathVariable int id) {
		this.vehiculoService.eliminar(id);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizarVehiculo(@RequestBody Vehiculo vehiculo, @PathVariable int id) {
		vehiculo.setId(id);
		this.vehiculoService.actualizar(vehiculo);
	}

	// Retirar Vehiculo en Reserva
	@GetMapping(path = "/buscarVehiculoReservado/{reserva}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reserva> buscarVehiculoReservado(@PathVariable String reserva) {
		Reserva check = this.gestorEmpleadoService.retirarVehiculoReservado(reserva);
		return ResponseEntity.status(HttpStatus.OK).body(check);
	}

	@GetMapping(path = "/buscarVehiculoSinReserva/{reserva}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reserva> buscarVehiculoSinReserva(@PathVariable String reserva) {
		Reserva r = this.gestorEmpleadoService.retirarVehiculoSinReserva(reserva);
		return ResponseEntity.status(HttpStatus.OK).body(r);
	}

	@GetMapping(path = "/disponibleVehiculo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VehiculoTo>> buscarVehiculoDisponible(@PathVariable int id, @RequestParam String marca,
			@RequestParam String modelo) {
		List<VehiculoTo> lsto = this.gestorClienteService.buscarVehiculoToDisponble(marca, modelo);
		VehiculoTo v = this.vehiculoService.buscarId(id);

		for (VehiculoTo vehiculoTo : lsto) {
			Link link = linkTo(methodOn(VehiculoControllerRestFull.class).consultarVehiculosPorMarca(v.getMarca()))
					.withRel("Id Marca Vehiculo");
			vehiculoTo.add(link);
		}

		return ResponseEntity.status(HttpStatus.OK).body(lsto);
	}

	@GetMapping(path = "/vehiculosOriginales", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Vehiculo>> observarVehiculos() {
		return ResponseEntity.status(HttpStatus.OK).body(this.vehiculoService.observarTodos());
	}

}
