package co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.servicios;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos.RegistroParqueoDTO;

public interface CalcularSalidaService {
	
	public RegistroParqueoDTO calcularSalida(String placa);
}
