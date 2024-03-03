package com.uce.edu.demo.repository;

import java.util.List;

import com.uce.edu.demo.modelo.Cliente;

public interface IClienteRepository {
	
	public void insertar(Cliente cliente);
	public void actualizar(Cliente cliente);
	public Cliente buscar(Integer id);
	public void eliminar(Integer id);

	public Cliente buscarPorCedula(String cedula);
	public List<Cliente> buscarPorApellido(String apellido);
}
