package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.excepciones.EstacionamientoException;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.repositorio.RepositorioRegistroParqueo;

@Service
public class Vigilante {

	public static final String MOTOS_SIN_CUPO = "Actualmente no hay espacio disponible para motos.";
	public static final String CARROS_SIN_CUPO = "Actualmente no hay espacio disponible para carros.";
	public static final String DIA_NO_HABIL = "El vehículo no puede ingresar porque no es un día hábil.";
	public static final String PLACA_DUPLICADA = "Actualmente hay un vehículo ingresado con la misma placa.";

	public static final int CUPO_MOTOS = 10;
	public static final int CUPO_CARROS = 20;

	public static final double VALOR_DIA_MOTO = 4000;
	public static final double VALOR_DIA_CARRO = 8000;
	public static final double VALOR_HORA_MOTO = 500;
	public static final double VALOR_HORA_CARRO = 1000;
	public static final double ADICION_MOTO_PESADA = 2000;

	private RepositorioRegistroParqueo repositorioRegistroParqueo;

	@Autowired
	public Vigilante(RepositorioRegistroParqueo repositorioRegistroParqueo) {
		this.repositorioRegistroParqueo = repositorioRegistroParqueo;
	}

	public RegistroParqueo ingresarVehiculo(SolicitudIngreso solicitudIngreso) {
		try {
			repositorioRegistroParqueo.buscarVehiculoIngresado(solicitudIngreso.getPlaca());
			throw new EstacionamientoException(PLACA_DUPLICADA);			
		} catch (EstacionamientoException exception) {
			if(exception.getMessage().equals(PLACA_DUPLICADA))
				throw exception;
		}

		validarDiaHabil(solicitudIngreso);
		validarCupo(solicitudIngreso);

		RegistroParqueo registroParqueo = new RegistroParqueo(null, solicitudIngreso.getFecha(), null,
				solicitudIngreso.getTipoVehiculo(), solicitudIngreso.getEsMotoAltoCilindraje(),
				solicitudIngreso.getPlaca(), 0.0);

		return repositorioRegistroParqueo.guardarRegistroParqueo(registroParqueo);
	}

	public void validarDiaHabil(SolicitudIngreso solicitudIngreso) {
		if (solicitudIngreso.getPlaca().charAt(0) == 'A') {
			Calendar fecha = solicitudIngreso.getFecha();

			if (fecha.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY
					&& fecha.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
				throw new EstacionamientoException(DIA_NO_HABIL);
		}
	}

	public void validarCupo(SolicitudIngreso solicitudIngreso) {
		String tipo = solicitudIngreso.getTipoVehiculo();
		int cantidadVehiculos = repositorioRegistroParqueo.cantidadVehiculosPorTipo(tipo);

		if (tipo.equals(TipoVehiculoEnum.MOTO.name()) && cantidadVehiculos == CUPO_MOTOS)
			throw new EstacionamientoException(MOTOS_SIN_CUPO);
		if (tipo.equals(TipoVehiculoEnum.CARRO.name()) && cantidadVehiculos == CUPO_CARROS)
			throw new EstacionamientoException(CARROS_SIN_CUPO);
	}

	public RegistroParqueo calcularSalida(String placa) {
		RegistroParqueo registroParqueo = repositorioRegistroParqueo.buscarVehiculoIngresado(placa);

		registroParqueo.setFechaSalida(Calendar.getInstance());
		double valor = calcularValor(registroParqueo.getFechaIngreso(), registroParqueo.getFechaSalida(),
				registroParqueo.getTipoVehiculo(), registroParqueo.getEsMotoAltoCilindraje());

		registroParqueo.setValor(valor);
		return registroParqueo;
	}

	public double calcularValor(Calendar fechaIngreso, Calendar fechaSalida, String tipoVehiculo,
			char esMotoAltoCilindraje) {

		final long miliSegundosPorHora = 3600000;
		final long miliSegundosPorDia = miliSegundosPorHora * 24;

		long diferencia = fechaSalida.getTimeInMillis() - fechaIngreso.getTimeInMillis();
		int dias = (int) (diferencia / miliSegundosPorDia);
		diferencia = diferencia - (dias * miliSegundosPorDia);
		int horas = (int) (diferencia / miliSegundosPorHora);
		diferencia = diferencia - (horas * miliSegundosPorHora);
		if (diferencia % miliSegundosPorHora > 0)
			horas++;

		double valorFacturado = tipoVehiculo.equals(TipoVehiculoEnum.MOTO.name())
				? dias * VALOR_DIA_MOTO + horas * VALOR_HORA_MOTO
				: dias * VALOR_DIA_CARRO + horas * VALOR_HORA_CARRO;

		return (esMotoAltoCilindraje == 'S') ? valorFacturado + ADICION_MOTO_PESADA : valorFacturado;
	}

	public RegistroParqueo sacarVehiculo(RegistroParqueo registroParqueo) {
		return repositorioRegistroParqueo.guardarRegistroParqueo(registroParqueo);
	}

	public List<RegistroParqueo> darVehiculosIngresados() {
		return repositorioRegistroParqueo.darVehiculosIngresados();
	}

	public List<String> darTiposVehiculo() {
		List<String> tiposVehiculo = new ArrayList<>();

		Arrays.asList(TipoVehiculoEnum.values()).forEach(tipoVehiculo -> tiposVehiculo.add(tipoVehiculo.name()));

		return tiposVehiculo;
	}

}
