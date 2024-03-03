package com.uce.edu.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.uce.edu.demo.modelo.Reserva;
import com.uce.edu.demo.service.IReservaService;

@RestController
@RequestMapping(path = "/reservas")
public class ReservaControllerRestFull {

	@Autowired
	private IReservaService reservaService;

	// RESERVA
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void reservaGuadar(@RequestBody Reserva reserva) {
		this.reservaService.insertar(reserva);
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reserva> buscarforId(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.reservaService.buscarPorId(id));
	}

	@GetMapping(path = "/fechasReservas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Reserva>> buscarFechasDispoFechas(@RequestParam LocalDateTime fechaInicio,
			@RequestParam LocalDateTime fechaFin) {
		List<Reserva> lsres = this.reservaService.buscarReservaFechas(fechaInicio, fechaFin);
		return ResponseEntity.status(HttpStatus.OK).body(lsres);
	}

	@GetMapping(path = "/reserva/{numero}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reserva> buscarReservaNumero(@PathVariable String numero) {
		Reserva r = this.reservaService.buscarPorNumero(numero);
		return ResponseEntity.status(HttpStatus.OK).body(r);
	}

	@DeleteMapping(path = "/{id}")
	public void eliminarReserva(@PathVariable int id) {
		this.reservaService.eliminar(id);
	}
}
