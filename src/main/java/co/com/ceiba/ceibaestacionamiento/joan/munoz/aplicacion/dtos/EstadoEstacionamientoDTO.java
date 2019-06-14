package co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos;

import java.util.List;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import lombok.Getter;

@Getter
public class EstadoEstacionamientoDTO {

	private List<String> tiposVehiculo;
	private List<RegistroParqueo> vehiculosIngresados;
	
	public EstadoEstacionamientoDTO(List<String> tiposVehiculo, List<RegistroParqueo> vehiculosIngresados) {
		this.tiposVehiculo = tiposVehiculo;
		this.vehiculosIngresados = vehiculosIngresados;
	}
}
