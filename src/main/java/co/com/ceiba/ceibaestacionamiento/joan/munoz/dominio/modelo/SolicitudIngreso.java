package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;

@Getter
public class SolicitudIngreso {
	
	private String tipoVehiculo;
	private String placa;
	private char esMotoAltoCilindraje;
	private Calendar fecha;
	
	@Autowired
	public SolicitudIngreso(String tipoVehiculo, String placa, char esMotoAltoCilindraje, Calendar fecha) {
		this.tipoVehiculo = tipoVehiculo;
		this.placa = placa;
		this.esMotoAltoCilindraje = esMotoAltoCilindraje;
		this.fecha = fecha;
	}
}
