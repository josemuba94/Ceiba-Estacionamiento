package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo;

import java.util.Calendar;

public class RegistroParqueo {

	private Long id;
	private Calendar fechaIngreso;
	private Calendar fechaSalida;
	private TipoVehiculoEnum tipoVehiculo;
	private char esMotoPesada;
	private String placa;
	private double valorFacturado;

	public RegistroParqueo() {

	}

	public RegistroParqueo(Long id, Calendar fechaIngreso, Calendar fechaSalida, TipoVehiculoEnum tipoVehiculo,
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

	public void setFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Calendar getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public TipoVehiculoEnum getTipoVehiculo() {
		return tipoVehiculo;
	}

	public char getEsMotoPesada() {
		return esMotoPesada;
	}

	public String getPlaca() {
		return placa;
	}

	public double getValorFacturado() {
		return valorFacturado;
	}

	public void setValorFacturado(double valorFacturado) {
		this.valorFacturado = valorFacturado;
	}
}
