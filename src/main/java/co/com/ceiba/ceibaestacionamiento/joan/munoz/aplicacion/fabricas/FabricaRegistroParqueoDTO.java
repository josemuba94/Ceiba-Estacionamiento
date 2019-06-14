package co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.fabricas;

import org.springframework.stereotype.Service;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos.RegistroParqueoDTO;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;

@Service
public class FabricaRegistroParqueoDTO {

	public RegistroParqueo convertirDTODominio(RegistroParqueoDTO registroParqueoDTO) {
		return new RegistroParqueo(registroParqueoDTO.getId(), registroParqueoDTO.getFechaIngreso(),
				registroParqueoDTO.getFechaSalida(), registroParqueoDTO.getTipoVehiculo(),
				registroParqueoDTO.getEsMotoAltoCilindraje(), registroParqueoDTO.getPlaca(),
				registroParqueoDTO.getValor());
	}

	public RegistroParqueoDTO convertirDominioDTO(RegistroParqueo registroParqueo) {
		return new RegistroParqueoDTO(registroParqueo.getId(), registroParqueo.getFechaIngreso(),
				registroParqueo.getFechaSalida(), registroParqueo.getTipoVehiculo(),
				registroParqueo.getEsMotoAltoCilindraje(), registroParqueo.getPlaca(), registroParqueo.getValor());
	}
}
