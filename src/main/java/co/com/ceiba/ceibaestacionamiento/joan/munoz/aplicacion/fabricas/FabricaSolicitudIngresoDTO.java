package co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.fabricas;

import org.springframework.stereotype.Component;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos.SolicitudIngresoDTO;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.SolicitudIngreso;

@Component
public class FabricaSolicitudIngresoDTO {

	public SolicitudIngreso convertirDTODominio(SolicitudIngresoDTO solicitudIngresoDTO) {
		return new SolicitudIngreso(solicitudIngresoDTO.getTipoVehiculo(), solicitudIngresoDTO.getPlaca(),
				solicitudIngresoDTO.isMotoAltoCilindraje(), solicitudIngresoDTO.getFecha());
	}
}
