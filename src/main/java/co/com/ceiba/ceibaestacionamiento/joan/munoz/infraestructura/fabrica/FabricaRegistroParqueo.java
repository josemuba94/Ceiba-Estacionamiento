package co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.fabrica;

import org.springframework.stereotype.Service;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.entidades.RegistroParqueoEntity;

@Service
public class FabricaRegistroParqueo {

	public RegistroParqueoEntity convertiraDominioEntidad(RegistroParqueo registroParqueo) {
		RegistroParqueoEntity registroParqueoEntity = new RegistroParqueoEntity();
		registroParqueoEntity.setId(registroParqueo.getId());
		registroParqueoEntity.setFechaIngreso(registroParqueo.getFechaIngreso());
		registroParqueoEntity.setFechaSalida(registroParqueo.getFechaSalida());
		registroParqueoEntity.setTipoVehiculo(registroParqueo.getTipoVehiculo());
		registroParqueoEntity.setEsMotoAltoCilindraje(registroParqueo.getEsMotoAltoCilindraje());
		registroParqueoEntity.setPlaca(registroParqueo.getPlaca());
		registroParqueoEntity.setValor(registroParqueo.getValor());

		return registroParqueoEntity;
	}

	public RegistroParqueo convertirEntidadDominio(RegistroParqueoEntity registroParqueoEntity) {
		return new RegistroParqueo(registroParqueoEntity.getId(), registroParqueoEntity.getFechaIngreso(),
				registroParqueoEntity.getFechaSalida(), registroParqueoEntity.getTipoVehiculo(),
				registroParqueoEntity.getEsMotoAltoCilindraje(), registroParqueoEntity.getPlaca(),
				registroParqueoEntity.getValor());
	}

}
