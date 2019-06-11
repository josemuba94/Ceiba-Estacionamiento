package co.com.ceiba.ceibaestacionamiento.joan.munoz.fabrica;

import java.util.Calendar;
import java.util.GregorianCalendar;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.TipoVehiculoEnum;

public class FabricaRegistroParqueoTest {
	
	private Long id;
	private Calendar fechaIngreso;
	private Calendar fechaSalida;
	private TipoVehiculoEnum tipoVehiculo;
	private char esMotoPesada;
	private String placa;
	private double valorFacturado;
	
	public FabricaRegistroParqueoTest(boolean conSalidaFacturada) {
		this.id = 123L;
		this.fechaIngreso = new GregorianCalendar(2019, Calendar.JUNE, 10, 12, 22); 
		this.tipoVehiculo = TipoVehiculoEnum.MOTO; 
		this.esMotoPesada = 'S';
		this.placa = "VKY61C"; 
		
		if ( conSalidaFacturada ) {
			this.fechaSalida = new GregorianCalendar(2019, Calendar.JUNE, 10, 12, 57);
			this.valorFacturado = 500; 
		}
	}

	public FabricaRegistroParqueoTest conId(Long id) {
		this.id = id;
		return this;
	}

	public FabricaRegistroParqueoTest conFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
		return this;
	}

	public FabricaRegistroParqueoTest conFechaSalida(Calendar fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}

	public FabricaRegistroParqueoTest conTipoVehiculo(TipoVehiculoEnum tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
		return this;
	}

	public FabricaRegistroParqueoTest conEsMotoPesada(char esMotoPesada) {
		this.esMotoPesada = esMotoPesada;
		return this;
	}

	public FabricaRegistroParqueoTest conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public FabricaRegistroParqueoTest conValorFacturado(double valorFacturado) {
		this.valorFacturado = valorFacturado;
		return this;
	}

	public RegistroParqueo construirRegistroVehiculo () {
		return new RegistroParqueo(id, fechaIngreso, fechaSalida, tipoVehiculo, esMotoPesada, placa, valorFacturado);
	}	
}

