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
	private char esMotoPesada;
	private String placa;
	private double valorFacturado;
	
	public RegistroParqueoDTO() {

	}

	public RegistroParqueoDTO(Long id, Calendar fechaIngreso, Calendar fechaSalida, String tipoVehiculo,
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
