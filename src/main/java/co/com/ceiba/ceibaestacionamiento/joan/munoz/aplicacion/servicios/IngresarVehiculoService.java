package co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.servicios;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos.RegistroParqueoDTO;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos.SolicitudIngresoDTO;

public interface IngresarVehiculoService {

	public RegistroParqueoDTO ingresarVehiculo(SolicitudIngresoDTO solicitudIngresoDTO);
}
