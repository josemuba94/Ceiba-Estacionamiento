package co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.fabrica;

import org.springframework.stereotype.Service;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos.RegistroParqueoDTO;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.excepciones.EstacionamientoException;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.TipoVehiculoEnum;

@Service
public class AplicacionFactory {
	
	public TipoVehiculoEnum obtenerTipoVehiculo(String tipoVehiculo) {
		for (TipoVehiculoEnum tipoVehiculoValido : TipoVehiculoEnum.values())
			if (tipoVehiculoValido.name().equals(tipoVehiculo))
				return tipoVehiculoValido;
		throw new EstacionamientoException("El parqueadero solo admite carros y motos.");
	}

	public RegistroParqueo convertirDTODominio(RegistroParqueoDTO registroParqueoDTO) {
		TipoVehiculoEnum tipoVehiculo = obtenerTipoVehiculo(registroParqueoDTO.getTipoVehiculo());

		return new RegistroParqueo(registroParqueoDTO.getId(), registroParqueoDTO.getFechaIngreso(),
				registroParqueoDTO.getFechaSalida(), tipoVehiculo, registroParqueoDTO.getEsMotoPesada(),
				registroParqueoDTO.getPlaca(), registroParqueoDTO.getValorFacturado());
	}

	public RegistroParqueoDTO convertirDominioDTO(RegistroParqueo registroParqueo) {
		return new RegistroParqueoDTO(registroParqueo.getId(), registroParqueo.getFechaIngreso(),
				registroParqueo.getFechaSalida(), registroParqueo.getTipoVehiculo().name(),
				registroParqueo.getEsMotoPesada(), registroParqueo.getPlaca(), registroParqueo.getValorFacturado());
	}
}
