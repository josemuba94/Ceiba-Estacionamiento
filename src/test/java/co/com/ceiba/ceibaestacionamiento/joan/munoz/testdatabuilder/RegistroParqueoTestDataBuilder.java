package co.com.ceiba.ceibaestacionamiento.joan.munoz.testdatabuilder;

import java.util.Calendar;
import java.util.GregorianCalendar;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.TipoVehiculoEnum;

public class RegistroParqueoTestDataBuilder {

	public static final boolean CON_SALIDA_FACTURADA = true;
	public static final boolean SIN_SALIDA_FACTURADA = false;

	private Long id;
	private Calendar fechaIngreso;
	private Calendar fechaSalida;
	private TipoVehiculoEnum tipoVehiculo;
	private char esMotoPesada;
	private String placa;
	private double valorFacturado;

	public RegistroParqueoTestDataBuilder(boolean salidaFacturada) {
		this.fechaIngreso = new GregorianCalendar(2019, Calendar.JUNE, 11, 12, 22);
		this.tipoVehiculo = TipoVehiculoEnum.MOTO;
		this.esMotoPesada = 'S';
		this.placa = "DTN28C";

		if (salidaFacturada) {
			this.id = 123L;
			this.fechaSalida = new GregorianCalendar(2019, Calendar.JUNE, 11, 12, 57);
			this.valorFacturado = 500;
		}
	}

	public RegistroParqueoTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}

	public RegistroParqueoTestDataBuilder conFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public RegistroParqueoTestDataBuilder conFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}

	public RegistroParqueoTestDataBuilder conTipoVehiculo(TipoVehiculoEnum tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public RegistroParqueoTestDataBuilder conEsMotoPesada(char esMotoPesada) {
		this.esMotoPesada = esMotoPesada;
		return this;
	}

	public RegistroParqueoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public RegistroParqueoTestDataBuilder conValorFacturado(double valorFacturado) {
		this.valorFacturado = valorFacturado;
		return this;
	}

	public RegistroParqueo construirRegistroParqueo() {
		return new RegistroParqueo(id, fechaIngreso, fechaSalida, tipoVehiculo, esMotoPesada, placa, valorFacturado);
	}
}
