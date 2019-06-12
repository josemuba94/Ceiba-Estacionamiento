package co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos;

import java.util.Calendar;

public class RegistroParqueoDTO {

	private Long id;
	private Calendar fechaIngreso;
	private Calendar fechaSalida;
	private String tipoVehiculo;
	private char esMotoPesada;
	private String placa;
	private double valorFacturado;

	public RegistroParqueoDTO() {

	}

	public RegistroParqueoDTO(Long id, Calendar fechaIngreso, Calendar fechaSalida, String tipoVehiculo,
			char esMotoPesada, String placa, double valorFacturado) {
		this.id = id;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.tipoVehiculo = tipoVehiculo;
		this.esMotoPesada = esMotoPesada;
		this.placa = placa;
		this.valorFacturado = valorFacturado;
	}

	public Long getId() {
		return id;
	}

	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}

	public Calendar getFechaSalida() {
		return fechaSalida;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public char getEsMotoPesada() {
		return esMotoPesada;
	}

	public void setEsMotoPesada(char esMotoPesada) {
		this.esMotoPesada = esMotoPesada;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public double getValorFacturado() {
		return valorFacturado;
	}
}
