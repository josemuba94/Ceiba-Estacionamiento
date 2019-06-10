package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "Registros_Vehiculo")
public class EntidadRegistroVehiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date fechaIngreso;	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaSalida;	
	private String tipoVehiculo;
	private String placa;
	private int valorFacturado;
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
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
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