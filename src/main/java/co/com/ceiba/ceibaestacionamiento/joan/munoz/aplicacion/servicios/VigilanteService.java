package co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos.RegistroParqueoDTO;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.fabrica.AplicacionFactory;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.IVigilante;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;

@Service
public class VigilanteService implements RegistrarVehiculoService {

	@Autowired
	private IVigilante vigilante;
	@Autowired
	private AplicacionFactory aplicacionFactory;

	@Override
	public RegistroParqueoDTO registrarIngresoVehiculo(RegistroParqueoDTO registroParqueoDTO) {
		RegistroParqueo registroParqueo = aplicacionFactory.convertirDTODominio(registroParqueoDTO);
		RegistroParqueo registroParqueoAlmacenado = vigilante.registrarIngresoVehiculo(registroParqueo);
		return aplicacionFactory.convertirDominioDTO(registroParqueoAlmacenado);
	}

}
