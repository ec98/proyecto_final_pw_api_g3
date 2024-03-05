package com.uce.edu.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.modelo.Vehiculo;
import com.uce.edu.demo.repository.IVehiculoRepository;
import com.uce.edu.demo.service.to.VehiculoTo;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepository vehiculoRepository;

	@Override
	public void insertar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.vehiculoRepository.insertar(vehiculo);
	}

	@Override
	public void actualizar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.vehiculoRepository.actualizar(vehiculo);
	}

	@Override
	public Vehiculo buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.vehiculoRepository.buscar(id);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.vehiculoRepository.eliminar(id);
	}

	@Override
	public Vehiculo buscarPorPlaca(String placa) {
		// TODO Auto-generated method stub
		return this.vehiculoRepository.buscarPorPlaca(placa);
	}

	@Override
	public List<Vehiculo> buscarPorMarcaModelo(String marca, String modelo) {
		// TODO Auto-generated method stub
		return this.vehiculoRepository.buscarPorMarcaModelo(marca, modelo);
	}

	@Override
	public List<Vehiculo> buscarPorMarca(String marca) {
		// TODO Auto-generated method stub
		return this.vehiculoRepository.buscarPorMarca(marca);
	}

	@Override
	public List<VehiculoTo> buscarVehiculoTo(String marca, String modelo) {
		// TODO Auto-generated method stub
		return this.vehiculoRepository.buscarVehiculoTo(marca, modelo);
	}

	@Override
	public List<VehiculoTo> buscarTodos() {
		// TODO Auto-generated method stub

		List<Vehiculo> ls = this.vehiculoRepository.buscarAll();
		List<VehiculoTo> lsfinal = new ArrayList<VehiculoTo>();

		for (Vehiculo vehiculo : ls) {
			VehiculoTo vlo = convertTo(vehiculo);
			lsfinal.add(vlo);
		}

		return lsfinal;
	}

	private VehiculoTo convertTo(Vehiculo vehiculo) {
		VehiculoTo vto = new VehiculoTo();
		vto.setId(vehiculo.getId());
		vto.setPlaca(vehiculo.getPlaca());
		vto.setModelo(vehiculo.getModelo());
		vto.setMarca(vehiculo.getMarca());
		vto.setEstado(vehiculo.getEstado());
		vto.setValorDia(vehiculo.getValorDia());
		return vto;
	}

	@Override
	public List<VehiculoTo> buscarPorMarcaTo(String marca) {
		// TODO Auto-generated method stub
		List<Vehiculo> ls = this.vehiculoRepository.buscarPorMarca(marca);
		List<VehiculoTo> lsfinal = new ArrayList<VehiculoTo>();

		for (Vehiculo vehiculo : ls) {
			VehiculoTo vlo = convertTo(vehiculo);
			lsfinal.add(vlo);
		}

		return lsfinal;
	}

	@Override
	public VehiculoTo buscarId(Integer id) {
		// TODO Auto-generated method stub
		VehiculoTo vto = this.convertTo(this.buscar(id));
		return vto;
	}

	@Override
	public List<Vehiculo> observarTodos() {
		// TODO Auto-generated method stub
		return this.vehiculoRepository.buscarAll();
	}

	@Override
	public void updateMarcaVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.vehiculoRepository.updateVehiculoPorMarca(vehiculo);
	}

}