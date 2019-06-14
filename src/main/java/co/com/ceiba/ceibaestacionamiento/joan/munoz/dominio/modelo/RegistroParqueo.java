package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo;

import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

@Getter
public class RegistroParqueo {

	private Long id;
	private Calendar fechaIngreso;
	@Setter private Calendar fechaSalida;
	private String tipoVehiculo;
	private char esMotoAltoCilindraje;
	private String placa;
	@Setter private double valor;

	public RegistroParqueo(Long id, Calendar fechaIngreso, Calendar fechaSalida, String tipoVehiculo,
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
