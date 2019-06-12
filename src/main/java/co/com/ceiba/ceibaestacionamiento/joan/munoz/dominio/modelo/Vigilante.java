package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.excepciones.EstacionamientoException;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.fabrica.DominioFactory;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.entidades.RegistroParqueoEntity;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.repositorio.RepositorioRegistroParqueo;

@Service
public class Vigilante implements IVigilante {

	public static final String MOTOS_SIN_CUPO = "Actualmente no hay espacio disponible para motos.";
	public static final String CARROS_SIN_CUPO = "Actualmente no hay espacio disponible para carros.";
	public static final String DIA_NO_HABIL = "El vehículo no puede ingresar porque no es un día hábil.";
	public static final int CUPO_MOTOS = 10;
	public static final int CUPO_CARROS = 20;

	@Autowired
	private RepositorioRegistroParqueo repositorioRegistroParqueo;
	@Autowired
	private DominioFactory dominioFactory;

	public RegistroParqueo registrarIngresoVehiculo(RegistroParqueo registroParqueo) {
		registroParqueo.setFechaIngreso(Calendar.getInstance());
		realizarValidacionesIngreso(registroParqueo);

		RegistroParqueoEntity registroParqueoEntity = dominioFactory.convertiraDominioEntidad(registroParqueo);
		RegistroParqueoEntity registroParqueoAlmacenado = repositorioRegistroParqueo
				.saveAndFlush(registroParqueoEntity);
		return dominioFactory.convertirEntidadDominio(registroParqueoAlmacenado);
	}

	public void realizarValidacionesIngreso(RegistroParqueo registroParqueo) {

		String placa = registroParqueo.getPlaca();
		if (placa.charAt(0) == 'A') {
			Calendar fechaActual = registroParqueo.getFechaIngreso();
			if (fechaActual.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY
					&& fechaActual.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
				throw new EstacionamientoException(DIA_NO_HABIL);
		}

		TipoVehiculoEnum tipo = registroParqueo.getTipoVehiculo();
		int cantidadVehiculos = repositorioRegistroParqueo.cantidadVehiculosPorTipo(tipo.name());		
		if (tipo.equals(TipoVehiculoEnum.MOTO) && cantidadVehiculos == CUPO_MOTOS)
			throw new EstacionamientoException(MOTOS_SIN_CUPO);
		if (tipo.equals(TipoVehiculoEnum.CARRO) && cantidadVehiculos == CUPO_CARROS)
			throw new EstacionamientoException(CARROS_SIN_CUPO);
	}
}
