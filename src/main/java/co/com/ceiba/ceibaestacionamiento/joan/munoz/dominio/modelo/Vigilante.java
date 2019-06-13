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
	public static final String VEHICULO_NO_INGRESADO = "El vehículo no se encuentra dentro del estacionamiento.";

	public static final int CUPO_MOTOS = 10;
	public static final int CUPO_CARROS = 20;

	public static final double VALOR_DIA_MOTO = 4000;
	public static final double VALOR_DIA_CARRO = 8000;
	public static final double VALOR_HORA_MOTO = 500;
	public static final double VALOR_HORA_CARRO = 1000;
	public static final double ADICION_MOTO_PESADA = 2000;

	@Autowired
	private RepositorioRegistroParqueo repositorioRegistroParqueo;
	@Autowired
	private DominioFactory dominioFactory;

	@Override
	public RegistroParqueo registrarIngresoVehiculo(RegistroParqueo registroParqueo) {
		registroParqueo.setFechaIngreso(Calendar.getInstance());

		validarDiaHabil(registroParqueo);
		validarCupo(registroParqueo);

		RegistroParqueoEntity registroParqueoEntity = dominioFactory.convertiraDominioEntidad(registroParqueo);
		RegistroParqueoEntity registroParqueoAlmacenado = repositorioRegistroParqueo
				.saveAndFlush(registroParqueoEntity);
		return dominioFactory.convertirEntidadDominio(registroParqueoAlmacenado);
	}

	public void validarDiaHabil(RegistroParqueo registroParqueo) {
		String placa = registroParqueo.getPlaca();

		if (placa.charAt(0) == 'A') {
			Calendar fechaActual = registroParqueo.getFechaIngreso();
			if (fechaActual.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY
					&& fechaActual.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
				throw new EstacionamientoException(DIA_NO_HABIL);
		}
	}

	public void validarCupo(RegistroParqueo registroParqueo) {
		TipoVehiculoEnum tipo = registroParqueo.getTipoVehiculo();

		int cantidadVehiculos = repositorioRegistroParqueo.cantidadVehiculosPorTipo(tipo.name());
		if (tipo.equals(TipoVehiculoEnum.MOTO) && cantidadVehiculos == CUPO_MOTOS)
			throw new EstacionamientoException(MOTOS_SIN_CUPO);
		if (tipo.equals(TipoVehiculoEnum.CARRO) && cantidadVehiculos == CUPO_CARROS)
			throw new EstacionamientoException(CARROS_SIN_CUPO);
	}

	@Override
	public RegistroParqueo calcularSalida(String placa) {
		RegistroParqueoEntity registroParqueoEntity = repositorioRegistroParqueo.buscarVehiculoIngresado(placa);		
		try {
			RegistroParqueo registroParqueoEncontrado = dominioFactory.convertirEntidadDominio(registroParqueoEntity);			
			registroParqueoEncontrado.setFechaSalida(Calendar.getInstance());
			
			return obtenerRegistroCalculado(registroParqueoEncontrado);			
		} catch (Exception e) {
			throw new EstacionamientoException(VEHICULO_NO_INGRESADO);
		}
	}

	public RegistroParqueo obtenerRegistroCalculado(RegistroParqueo registroParqueo) {
		double valorFacturado = calcularValorFacturado(registroParqueo.getFechaIngreso(),
				registroParqueo.getFechaSalida(), registroParqueo.getTipoVehiculo(), registroParqueo.getEsMotoPesada());

		registroParqueo.setValorFacturado(valorFacturado);
		return registroParqueo;
	}

	public double calcularValorFacturado(Calendar fechaIngreso, Calendar fechaSalida, TipoVehiculoEnum tipoVehiculo,
			char esMotoPesada) {
		
		final long miliSegundosPorHora = 3600000;
		final long miliSegundosPorDia = miliSegundosPorHora * 24;

		long diferencia = fechaSalida.getTimeInMillis() - fechaIngreso.getTimeInMillis();
		int dias = (int) (diferencia / miliSegundosPorDia);
		diferencia = diferencia - (dias*miliSegundosPorDia);
		int horas = (int) (diferencia / miliSegundosPorHora);
		diferencia = diferencia - (horas*miliSegundosPorHora);
		if (diferencia % miliSegundosPorHora > 0)
			horas++;

		double valorFacturado = tipoVehiculo.equals(TipoVehiculoEnum.MOTO) ? dias * VALOR_DIA_MOTO + horas * VALOR_HORA_MOTO
				: dias * VALOR_DIA_CARRO + horas * VALOR_HORA_CARRO;
				
		return (esMotoPesada == 'S') ? valorFacturado + ADICION_MOTO_PESADA : valorFacturado;
	}
}
