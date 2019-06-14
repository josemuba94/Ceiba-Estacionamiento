package co.com.ceiba.ceibaestacionamiento.joan.munoz.datatestbuilder;

import java.util.Calendar;
import java.util.GregorianCalendar;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.SolicitudIngreso;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.TipoVehiculoEnum;

public class SolicitudIngresoDataTestBuilder {

	static final String TIPO_VEHICULO = TipoVehiculoEnum.MOTO.name();
	static final char MOTO_ALTO_CILINDRAJE = 'S';
	static final String PLACA_INICIAL = "DTN28C";
	
	String tipoVehiculo;
	char esMotoAltoCilindraje;
	String placa;
	Calendar fecha;

	public SolicitudIngresoDataTestBuilder() {
		this.tipoVehiculo = TIPO_VEHICULO;
		this.esMotoAltoCilindraje = MOTO_ALTO_CILINDRAJE;
		this.placa = PLACA_INICIAL;
		this.fecha = new GregorianCalendar(2019, Calendar.JUNE, 13, 12, 12);
	}

	public SolicitudIngresoDataTestBuilder conTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public SolicitudIngresoDataTestBuilder conEsMotoAltoCilindraje(char esMotoAltoCilindraje) {
		this.esMotoAltoCilindraje = esMotoAltoCilindraje;
		return this;
	}

	public SolicitudIngresoDataTestBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public SolicitudIngresoDataTestBuilder conFecha(Calendar fecha) {
		this.fecha = fecha;
		return this;
	}
	
	public SolicitudIngreso construirSolicitudIngreso() {
		return new SolicitudIngreso(tipoVehiculo, placa, esMotoAltoCilindraje, fecha);
	}
}
