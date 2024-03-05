package com.uce.edu.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.modelo.Vehiculo;
import com.uce.edu.demo.service.to.VehiculoTo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class VehiculoRepositoryImpl implements IVehiculoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.persist(vehiculo);
	}

	@Override
	public void actualizar(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.merge(vehiculo);
	}

	@Override
	public Vehiculo buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Vehiculo.class, id);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		this.entityManager.remove(this.buscar(id));
	}

	@Override
	public Vehiculo buscarPorPlaca(String placa) {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Vehiculo> myQuery = this.entityManager
					.createQuery("SELECT v FROM Vehiculo v WHERE v.placa = :placa", Vehiculo.class);
			myQuery.setParameter("placa", placa);
			return myQuery.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Vehiculo> buscarPorMarcaModelo(String marca, String modelo) {
		// TODO Auto-generated method stub
		TypedQuery<Vehiculo> myQuery = this.entityManager
				.createQuery("SELECT v FROM Vehiculo v WHERE v.marca = :marca AND v.modelo = :modelo", Vehiculo.class);
		myQuery.setParameter("marca", marca);
		myQuery.setParameter("modelo", modelo);
		return myQuery.getResultList();
	}

	@Override
	public List<Vehiculo> buscarPorMarca(String marca) {
		// TODO Auto-generated method stub
		TypedQuery<Vehiculo> myQuery = this.entityManager.createQuery("SELECT v FROM Vehiculo v WHERE v.marca = :marca",
				Vehiculo.class);
		myQuery.setParameter("marca", marca);
		return myQuery.getResultList();
	}

	@Override
	public List<VehiculoTo> buscarVehiculoTo(String marca, String modelo) {
		// TODO Auto-generated method stub
		TypedQuery<VehiculoTo> myQuery = this.entityManager.createQuery(
				"SELECT NEW com.uce.edu.demo.to.VehiculoTo (v.placa, v.marca, v.modelo, v.anioFabricacion, v.estado, v.valorDia) FROM Vehiculo v WHERE v.marca = :marca AND v.modelo = :modelo",
				VehiculoTo.class);
		myQuery.setParameter("marca", marca);
		myQuery.setParameter("modelo", modelo);
		return myQuery.getResultList();
	}

	@Override
	public List<Vehiculo> buscarAll() {
		// TODO Auto-generated method stub
		TypedQuery<Vehiculo> mQuery = this.entityManager.createQuery("SELECT v FROM Vehiculo v", Vehiculo.class);
		return mQuery.getResultList();
	}

	@Override
	public void updateVehiculoPorMarca(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.merge(vehiculo);
	}

}
