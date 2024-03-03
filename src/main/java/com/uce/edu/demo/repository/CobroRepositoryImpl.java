package com.uce.edu.demo.repository;

import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.modelo.Cobro;
import com.uce.edu.demo.modelo.Reserva;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CobroRepositoryImpl implements ICobroRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Cobro cobro) {
		// TODO Auto-generated method stub
		this.entityManager.persist(cobro);
	}

	@Override
	public Cobro buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Cobro.class, id);
	}

	@Override
	public Cobro crearCobro(String tarjeta, Reserva reserva) {
		// TODO Auto-generated method stub
		Cobro cobro = new Cobro();
		cobro.setFechaCobro(LocalDateTime.now());
		cobro.setReserva(reserva);
		cobro.setTarjeta(tarjeta);
		return cobro;
	}

}
