package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.modelo.Reserva;
import com.uce.edu.demo.repository.IReservaRepository;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IReservaRepository reservaRepository;

	@Override
	public void insertar(Reserva reserva) {
		// TODO Auto-generated method stub
		this.reservaRepository.insertar(reserva);
	}

	@Override
	public void actualizar(Reserva reserva) {
		// TODO Auto-generated method stub
		this.reservaRepository.actualizar(reserva);
	}

	@Override
	public Reserva buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.reservaRepository.buscar(id);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.reservaRepository.eliminar(id);
	}

	@Override
	public Reserva buscarReservaDisponibleFechas(String placa, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		// TODO Auto-generated method stub
		return this.reservaRepository.buscarReservaDisponibleFechas(placa, fechaInicio, fechaFin);
	}

	@Override
	public Reserva buscarPorNumero(String numero) {
		// TODO Auto-generated method stub
		return this.reservaRepository.buscarPorNumero(numero);
	}

	@Override
	public List<Reserva> reporteReservas() {
		// TODO Auto-generated method stub
		return this.reservaRepository.reporteReservas();
	}

	@Override
	public List<Reserva> buscarReservaFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		// TODO Auto-generated method stub
		return this.reservaRepository.buscarReservaFechas(fechaInicio, fechaFin);
	}
}
