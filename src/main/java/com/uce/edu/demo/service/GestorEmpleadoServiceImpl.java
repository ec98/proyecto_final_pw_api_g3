package com.uce.edu.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.modelo.Cliente;
import com.uce.edu.demo.modelo.Cobro;
import com.uce.edu.demo.modelo.Reserva;
import com.uce.edu.demo.modelo.Vehiculo;

@Service
public class GestorEmpleadoServiceImpl implements IGestorEmpleadoService {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IReservaService reservaService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IGestorClienteService gestorClienteService;

	@Autowired
	private ICobroService cobroService;

	@Override
	public void registrarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		cliente.setRegistro("E");
		this.clienteService.insertar(cliente);
	}

	@Override
	public void ingresarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		vehiculo.setEstado("D");
		this.vehiculoService.insertar(vehiculo);
	}

	@Override
	public Reserva retirarVehiculoReservado(String numeroReserva) {
		// TODO Auto-generated method stub
		Reserva reserva = this.reservaService.buscarPorNumero(numeroReserva);
		Vehiculo vehiculo = reserva.getVehiculo();
		if ((reserva.getEstado().equals("G")) && (vehiculo.getEstado().equals("D"))) { // si reserva es estado:generado
																						// y vehiculo es
																						// estado:disponible
			reserva.setEstado("E");
			vehiculo.setEstado("ND");

			this.reservaService.actualizar(reserva);
			this.vehiculoService.actualizar(vehiculo);
		}
		return reserva;
	}

	@Override
	public void actualizar(Cliente cliente) {
		// TODO Auto-generated method stub
		cliente.setRegistro("E");
		this.clienteService.actualizar(cliente);
	}

	@Override
	public void actualizarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		vehiculo.setEstado("D");
		this.vehiculoService.actualizar(vehiculo);
	}

	@Override
	public Reserva retirarVehiculoSinReserva(String numeroReserva) {
		// TODO Auto-generated method stub
		Reserva tmp = this.retirarVehiculoReservado(numeroReserva);
		Cobro c = this.cobroService.buscar(tmp.getId());

		Reserva r = this.gestorClienteService.calcularValores(c.getTarjeta(), tmp.getVehiculo(), tmp.getCliente(),
				tmp.getFechaInicio(), tmp.getFechaFin());
		Vehiculo v = r.getVehiculo();

		if (tmp != this.retirarVehiculoReservado(numeroReserva) && tmp != null) {
			if ((r.getEstado().equals("G")) && (v.getEstado().equals("D"))) {

				r.setEstado("E");
				v.setEstado("ND");
				this.reservaService.actualizar(r);
				this.vehiculoService.actualizar(v);
			}
		}

		return r;
	}

}
