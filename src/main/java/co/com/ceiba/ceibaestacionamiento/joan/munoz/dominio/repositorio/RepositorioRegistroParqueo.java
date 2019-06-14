package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.repositorio;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;

public interface RepositorioRegistroParqueo {

	public RegistroParqueo guardarRegistroParqueo(RegistroParqueo registroParqueo);

	public int cantidadVehiculosPorTipo(String tipo);
	
	public RegistroParqueo buscarVehiculoIngresado(String placa);
}
