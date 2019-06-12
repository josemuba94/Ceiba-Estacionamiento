package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.fabrica;

import org.springframework.stereotype.Service;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.TipoVehiculoEnum;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.entidades.RegistroParqueoEntity;

@Service
public class DominioFactory {

	public RegistroParqueoEntity convertiraDominioEntidad(RegistroParqueo registroParqueo) {
		RegistroParqueoEntity registroParqueoEntity = new RegistroParqueoEntity();
		registroParqueoEntity.setId(registroParqueo.getId());
		registroParqueoEntity.setFechaIngreso(registroParqueo.getFechaIngreso());
		registroParqueoEntity.setFechaSalida(registroParqueo.getFechaSalida());
		registroParqueoEntity.setTipoVehiculo(registroParqueo.getTipoVehiculo().name());
		registroParqueoEntity.setEsMotoPesada(registroParqueo.getEsMotoPesada());
		registroParqueoEntity.setPlaca(registroParqueo.getPlaca());
		registroParqueoEntity.setValorFacturado(registroParqueo.getValorFacturado());

		return registroParqueoEntity;
	}

	public RegistroParqueo convertirEntidadDominio(RegistroParqueoEntity registroParqueoEntity) {
		return new RegistroParqueo(registroParqueoEntity.getId(), registroParqueoEntity.getFechaIngreso(),
				registroParqueoEntity.getFechaSalida(),
				TipoVehiculoEnum.valueOf(registroParqueoEntity.getTipoVehiculo()),
				registroParqueoEntity.getEsMotoPesada(), registroParqueoEntity.getPlaca(),
				registroParqueoEntity.getValorFacturado());
	}

}
