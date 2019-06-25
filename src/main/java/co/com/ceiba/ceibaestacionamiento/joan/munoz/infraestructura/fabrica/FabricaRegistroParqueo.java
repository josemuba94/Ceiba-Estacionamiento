package co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.fabrica;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.entidades.RegistroParqueoEntity;

@Component
public class FabricaRegistroParqueo {

	public RegistroParqueoEntity convertiraDominioEntidad(RegistroParqueo registroParqueo) {
		RegistroParqueoEntity registroParqueoEntity = new RegistroParqueoEntity();
		registroParqueoEntity.setId(registroParqueo.getId());
		registroParqueoEntity.setFechaIngreso(registroParqueo.getFechaIngreso());
		registroParqueoEntity.setFechaSalida(registroParqueo.getFechaSalida());
		registroParqueoEntity.setTipoVehiculo(registroParqueo.getTipoVehiculo());
		registroParqueoEntity.setMotoAltoCilindraje(registroParqueo.isMotoAltoCilindraje());
		registroParqueoEntity.setPlaca(registroParqueo.getPlaca());
		registroParqueoEntity.setValor(registroParqueo.getValor());

		return registroParqueoEntity;
	}

	public RegistroParqueo convertirEntidadDominio(RegistroParqueoEntity registroParqueoEntity) {		
		return new RegistroParqueo(registroParqueoEntity.getId(), registroParqueoEntity.getFechaIngreso(),
				registroParqueoEntity.getFechaSalida(), registroParqueoEntity.getTipoVehiculo(),
				registroParqueoEntity.isMotoAltoCilindraje(), registroParqueoEntity.getPlaca(),
				registroParqueoEntity.getValor());
	}

	public List <RegistroParqueo> convertirListEntityListDominio(List<RegistroParqueoEntity> listadoVehiculosEntity) {
		List <RegistroParqueo> listadoVehiculos = new ArrayList<>();
		
		int cantidadVehiculos = listadoVehiculosEntity.size();
		for(int i = 0; i < cantidadVehiculos; i++) 
			listadoVehiculos.add(convertirEntidadDominio(listadoVehiculosEntity.get(i)));		
		
		return listadoVehiculos;
	}

}
