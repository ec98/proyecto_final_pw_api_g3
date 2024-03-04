package com.uce.edu.demo.service;

import com.uce.edu.demo.modelo.Cliente;
import com.uce.edu.demo.modelo.Reserva;
import com.uce.edu.demo.modelo.Vehiculo;

public interface IGestorEmpleadoService {

	public void registrarCliente(Cliente cliente);

	public void actualizar(Cliente cliente);

	public void ingresarVehiculo(Vehiculo vehiculo);

	public void actualizarVehiculo(Vehiculo vehiculo);

	public Reserva retirarVehiculoReservado(String numeroReserva);

	public Reserva retirarVehiculoSinReserva(String numeroReserva);

}
