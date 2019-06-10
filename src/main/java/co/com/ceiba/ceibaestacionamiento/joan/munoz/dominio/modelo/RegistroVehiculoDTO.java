package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo;

import java.util.Date;

public class RegistroVehiculoDTO {

	private Long id;
	private Date fechaIngreso;
	private Date fechaSalida;
	private char tipoVehiculo;
	private String placa;
	
	public RegistroVehiculoDTO() {
		
	}
	
	public RegistroVehiculoDTO(Long id, Date fechaIngreso, Date fechaSalida, char tipoVehiculo, String placa) {
		this.id = id;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.tipoVehiculo = tipoVehiculo;
	    this.placa = placa;
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
	public char getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(char tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
}
