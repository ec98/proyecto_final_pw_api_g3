package com.uce.edu.demo.service.to;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

public class VehiculoTo extends RepresentationModel<VehiculoTo> implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String placa;
	private String modelo;
	private String marca;
	private String anioFabricacion;
	private String estado;
	private BigDecimal valorDia;

	public VehiculoTo() {
		// TODO Auto-generated constructor stub
	}

	public VehiculoTo(String placa, String modelo, String marca, String anioFabricacion, String estado,
			BigDecimal valorDia) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.marca = marca;
		this.anioFabricacion = anioFabricacion;
		this.estado = estado;
		this.valorDia = valorDia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "VehiculoTo [placa=" + placa + ", modelo=" + modelo + ", marca=" + marca + ", anioFabricacion="
				+ anioFabricacion + ", estado=" + estado + ", valorDia=" + valorDia + "]";
	}

	// SET y GET
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getAnioFabricacion() {
		return anioFabricacion;
	}

	public void setAnioFabricacion(String anioFabricacion) {
		this.anioFabricacion = anioFabricacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigDecimal getValorDia() {
		return valorDia;
	}

	public void setValorDia(BigDecimal valorDia) {
		this.valorDia = valorDia;
	}

}
