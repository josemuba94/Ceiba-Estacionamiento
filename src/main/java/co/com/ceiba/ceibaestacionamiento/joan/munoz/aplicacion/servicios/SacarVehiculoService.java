package co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.servicios;

import org.springframework.web.bind.annotation.RequestBody;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos.RegistroParqueoDTO;

public interface SacarVehiculoService {

	public RegistroParqueoDTO sacarVehiculo(@RequestBody RegistroParqueoDTO registroParqueoDTO);
}
