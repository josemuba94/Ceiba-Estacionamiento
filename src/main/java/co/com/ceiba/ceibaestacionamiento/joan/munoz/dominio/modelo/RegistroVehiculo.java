package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo;

import java.util.Date;

public class RegistroVehiculo {

	private Long id;
	private Date fechaIngreso;
	private Date fechaSalida;
	private EnumTipoVehiculo tipoVehiculo;
	private String placa;
	private int valorFacturado;
	
	public RegistroVehiculo() {
		
	}
	
	public RegistroVehiculo(Long id, Date fechaIngreso, Date fechaSalida,
			EnumTipoVehiculo tipoVehiculo, String placa, int valorFacturado) {
		this.id = id;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.tipoVehiculo = tipoVehiculo;
	    this.placa = placa;
	    this.valorFacturado = valorFacturado;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public EnumTipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(EnumTipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public int getValorFacturado() {
		return valorFacturado;
	}
	public void setValorFacturado(int valorFacturado) {
		this.valorFacturado = valorFacturado;
	}
	
}
