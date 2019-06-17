package co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import lombok.Getter;

@Getter
public class EstadoEstacionamientoDTO {

	private List<String> tiposVehiculo;
	private List<VehiculoIngresadoDTO> vehiculosIngresados;

	public EstadoEstacionamientoDTO(List<String> tiposVehiculo, List<RegistroParqueo> vehiculosIngresados) {
		this.tiposVehiculo = tiposVehiculo;
		this.vehiculosIngresados = new ArrayList<>();
		mapearVehiculosIngresados(vehiculosIngresados);
	}

	public void mapearVehiculosIngresados(List<RegistroParqueo> vehiculosIngresados) {
		for (int i = 0; i < vehiculosIngresados.size(); i++)
			this.vehiculosIngresados.add(new VehiculoIngresadoDTO(vehiculosIngresados.get(i).getPlaca(), vehiculosIngresados.get(i).getTipoVehiculo(), vehiculosIngresados.get(i).getFechaIngreso()));
	}
}
