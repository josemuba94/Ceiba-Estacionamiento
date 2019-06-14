package co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos;

import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroParqueoDTO {
	
	private Long id;
	private Calendar fechaIngreso;
	private Calendar fechaSalida;
	private String tipoVehiculo;
	private char esMotoAltoCilindraje;
	private String placa;
	private double valor;
	
	public RegistroParqueoDTO() { // Requerido por el framework

	}

	public RegistroParqueoDTO(Long id, Calendar fechaIngreso, Calendar fechaSalida, String tipoVehiculo,
			char esMotoAltoCilindraje, String placa, double valor) {
		this.id = id;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.tipoVehiculo = tipoVehiculo;
		this.esMotoAltoCilindraje = esMotoAltoCilindraje;
		this.placa = placa;
		this.valor = valor;
	}
}
