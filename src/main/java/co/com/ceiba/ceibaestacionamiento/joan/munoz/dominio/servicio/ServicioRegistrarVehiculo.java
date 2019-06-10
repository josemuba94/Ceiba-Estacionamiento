package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.entidades.RegistroVehiculo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.repositorio.IRepositorioRegistroVehiculo;

@Service
public class ServicioRegistrarVehiculo implements IServicioRegistrarVehiculo {

	@Autowired
	private IRepositorioRegistroVehiculo repositorioRegistroVehiculo;

	@Override
	public RegistroVehiculo ingresarVehiculo(RegistroVehiculo registroVehiculo) {
		return repositorioRegistroVehiculo.saveAndFlush(registroVehiculo);
	}

}
