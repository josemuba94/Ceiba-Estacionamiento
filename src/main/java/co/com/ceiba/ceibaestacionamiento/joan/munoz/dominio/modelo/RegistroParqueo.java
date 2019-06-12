package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo;

import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroParqueo {

	private Long id;
	private Calendar fechaIngreso;
	private Calendar fechaSalida;
	private TipoVehiculoEnum tipoVehiculo;
	private char esMotoPesada;
	private String placa;
	private double valorFacturado;

	public RegistroParqueo() {

	}

	public RegistroParqueo(Long id, Calendar fechaIngreso, Calendar fechaSalida, TipoVehiculoEnum tipoVehiculo,
			char esMotoPesada, String placa, double valorFacturado) {
		this.id = id;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.tipoVehiculo = tipoVehiculo;
		this.esMotoPesada = esMotoPesada;
		this.placa = placa;
		this.valorFacturado = valorFacturado;
	}
}
