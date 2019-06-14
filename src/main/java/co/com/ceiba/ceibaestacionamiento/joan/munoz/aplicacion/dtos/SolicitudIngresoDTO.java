package co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolicitudIngresoDTO {
	
	private String tipoVehiculo;
	private String placa;
	private char esMotoAltoCilindraje;
	private Calendar fecha;
	
	public SolicitudIngresoDTO() {
		
	}
	
	@Autowired
	public SolicitudIngresoDTO(String tipoVehiculo, String placa, char esMotoAltoCilindraje, Calendar fecha) {
		this.tipoVehiculo = tipoVehiculo;
		this.placa = placa;
		this.esMotoAltoCilindraje = esMotoAltoCilindraje;
		this.fecha = fecha;
	}
}
