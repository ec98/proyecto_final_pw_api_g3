package com.uce.edu.demo.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "cobro")
@JsonIgnoreProperties(value = "reserva")
public class Cobro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cobr_seq_id")
	@SequenceGenerator(name = "cobr_seq_id", sequenceName = "cobr_seq_id", allocationSize = 1)
	@Column(name = "cobr_id")
	private Integer id;

	@Column(name = "cobr_tarjeta")
	private String tarjeta;

	@Column(name = "cobr_fecha", columnDefinition = "TIMESTAMP")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fechaCobro;

	@Column(name = "cobr_valor_subtotal")
	private BigDecimal valorSubtotal;

	@Column(name = "cobr_valor_iva")
	private BigDecimal valorIva;

	@Column(name = "cobr_valor_total_pagar")
	private BigDecimal valorTotalPagar;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cobr_rese_id")
	private Reserva reserva;

	@Override
	public String toString() {
		return "Cobro [id=" + id + ", tarjeta=" + tarjeta + ", fechaCobro=" + fechaCobro + ", valorSubtotal="
				+ valorSubtotal + ", valorIva=" + valorIva + ", valorTotalPagar=" + valorTotalPagar + "]";
	}

	// SET y GET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public LocalDateTime getFechaCobro() {
		return fechaCobro;
	}

	public void setFechaCobro(LocalDateTime fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}

	public BigDecimal getValorIva() {
		return valorIva;
	}

	public void setValorIva(BigDecimal valorIva) {
		this.valorIva = valorIva;
	}

	public BigDecimal getValorTotalPagar() {
		return valorTotalPagar;
	}

	public void setValorTotalPagar(BigDecimal valorTotalPagar) {
		this.valorTotalPagar = valorTotalPagar;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

}
