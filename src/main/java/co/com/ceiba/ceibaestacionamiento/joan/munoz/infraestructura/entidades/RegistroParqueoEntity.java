package co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.entidades;

import java.util.Calendar;

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
@Table (name = "Registros_Parqueo")
public class RegistroParqueoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Calendar fechaIngreso;	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaSalida;	
	@NotNull
	private String tipoVehiculo;
	@NotNull
	private char esMotoPesada;
	@NotNull
	private String placa;
	private double valorFacturado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void setValorFacturado(double valorFacturado) {
		this.valorFacturado = valorFacturado;
	}
	
}