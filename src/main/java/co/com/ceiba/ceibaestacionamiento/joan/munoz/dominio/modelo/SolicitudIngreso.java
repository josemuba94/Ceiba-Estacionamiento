package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo;

import java.util.Calendar;

import lombok.Getter;

@Getter
public class SolicitudIngreso {
	
	private String tipoVehiculo;
	private String placa;
	private boolean motoAltoCilindraje;
	private Calendar fecha;

	public SolicitudIngreso(String tipoVehiculo, String placa, boolean motoAltoCilindraje, Calendar fecha) {
		this.tipoVehiculo = tipoVehiculo;
		this.placa = placa;
		this.motoAltoCilindraje = motoAltoCilindraje;
		this.fecha = fecha;
	}
}
