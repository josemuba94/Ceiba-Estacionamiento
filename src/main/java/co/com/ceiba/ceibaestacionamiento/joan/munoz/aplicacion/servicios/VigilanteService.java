package co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos.EstadoEstacionamientoDTO;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos.RegistroParqueoDTO;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos.SolicitudIngresoDTO;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.fabricas.FabricaRegistroParqueoDTO;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.fabricas.FabricaSolicitudIngresoDTO;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.Vigilante;

@Service
public class VigilanteService
		implements IngresarVehiculoService, CalcularSalidaService, SacarVehiculoService, DarEstadoEstacionamiento {

	@Autowired
	private Vigilante vigilante;
	@Autowired
	private FabricaRegistroParqueoDTO fabricaRegistroParqueoDTO;
	@Autowired
	private FabricaSolicitudIngresoDTO fabricaSolicitudIngreso;

	@Override
	public RegistroParqueoDTO ingresarVehiculo(SolicitudIngresoDTO solicitudIngresoDTO) {
		RegistroParqueo registroParqueoAlmacenado = vigilante
				.ingresarVehiculo(fabricaSolicitudIngreso.convertirDTODominio(solicitudIngresoDTO));
		return fabricaRegistroParqueoDTO.convertirDominioDTO(registroParqueoAlmacenado);
	}

	@Override
	public RegistroParqueoDTO calcularSalida(String placa) {
		RegistroParqueo registroParqueoCalculado = vigilante.calcularSalida(placa);
		return fabricaRegistroParqueoDTO.convertirDominioDTO(registroParqueoCalculado);
	}

	@Override
	public RegistroParqueoDTO sacarVehiculo(RegistroParqueoDTO registroParqueoDTO) {
		RegistroParqueo registroParqueo = fabricaRegistroParqueoDTO.convertirDTODominio(registroParqueoDTO);
		return fabricaRegistroParqueoDTO.convertirDominioDTO(vigilante.sacarVehiculo(registroParqueo));
	}

	@Override
	public EstadoEstacionamientoDTO darVehiculosIngresados() {
		List<String> tiposVehiculo = vigilante.darTiposVehiculo();
		List<RegistroParqueo> vehiculosIngresados = vigilante.darVehiculosIngresados();
		return new EstadoEstacionamientoDTO(tiposVehiculo, vehiculosIngresados);
	}
}
