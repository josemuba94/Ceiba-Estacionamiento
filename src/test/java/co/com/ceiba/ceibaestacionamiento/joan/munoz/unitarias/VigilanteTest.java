package co.com.ceiba.ceibaestacionamiento.joan.munoz.unitarias;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.datatestbuilder.SolicitudIngresoDataTestBuilder;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.excepciones.EstacionamientoException;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.SolicitudIngreso;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.TipoVehiculoEnum;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.Vigilante;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.repositorio.RepositorioRegistroParqueo;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VigilanteTest {

	@Mock
	private RepositorioRegistroParqueo repositorioRegistroParqueo;
	@InjectMocks
	private Vigilante vigilante;

	@Test
	public void diaNoHabilTest() {
		// Arrange
		SolicitudIngreso solicitudIngreso = new SolicitudIngresoDataTestBuilder().conPlaca("AKY12A")
				.construirSolicitudIngreso();
		try {
			// Act
			vigilante.validarDiaHabil(solicitudIngreso);
			fail();
		} catch (EstacionamientoException excepcion) {
			// Assert
			assertEquals(Vigilante.DIA_NO_HABIL, excepcion.getMessage());
		}
	}

	@Test
	public void sinEspacioParaMotosTest() {
		// Arrange
		SolicitudIngreso solicitudIngreso = new SolicitudIngresoDataTestBuilder().construirSolicitudIngreso();

		when(repositorioRegistroParqueo.cantidadVehiculosPorTipo(solicitudIngreso.getTipoVehiculo()))
				.thenReturn(Vigilante.CUPO_MOTOS);
		try {
			// Act
			vigilante.validarCupo(solicitudIngreso);
			fail();
		} catch (EstacionamientoException excepcion) {
			// Assert
			assertEquals(Vigilante.MOTOS_SIN_CUPO, excepcion.getMessage());
		}
	}

	@Test
	public void sinEspacioParaCarrosTest() {
		// Arrange
		SolicitudIngreso solicitudIngreso = new SolicitudIngresoDataTestBuilder()
				.conTipoVehiculo(TipoVehiculoEnum.CARRO.name()).construirSolicitudIngreso();

		when(repositorioRegistroParqueo.cantidadVehiculosPorTipo(solicitudIngreso.getTipoVehiculo()))
				.thenReturn(Vigilante.CUPO_CARROS);
		try {
			// Act
			vigilante.validarCupo(solicitudIngreso);
			fail();
		} catch (EstacionamientoException excepcion) {
			// Assert
			assertEquals(Vigilante.CARROS_SIN_CUPO, excepcion.getMessage());
		}
	}

	@Test
	public void calcularValorMotoPesadaTest() {
		// Arrange
		Calendar fechaIngreso = new GregorianCalendar(2019, Calendar.JUNE, 13, 13, 13);
		Calendar fechaSalida  = new GregorianCalendar(2019, Calendar.JUNE, 15, 15, 15);
		
		// Act
		double valorFacturado = vigilante.calcularValor(fechaIngreso, fechaSalida, TipoVehiculoEnum.MOTO.name(), 'S');
		
		// Assert
		assertTrue(11500 == valorFacturado);
	}
	
	@Test
	public void calcularValorCarroTest() {
		// Arrange
		Calendar fechaIngreso = new GregorianCalendar(2019, Calendar.JUNE, 14, 13, 13);
		Calendar fechaSalida  = new GregorianCalendar(2019, Calendar.JUNE, 15, 15, 27);
		
		// Act
		double valorFacturado = vigilante.calcularValor(fechaIngreso, fechaSalida, TipoVehiculoEnum.CARRO.name(), 'N');
		
		// Assert
		assertTrue(11000 == valorFacturado);
	}
}
