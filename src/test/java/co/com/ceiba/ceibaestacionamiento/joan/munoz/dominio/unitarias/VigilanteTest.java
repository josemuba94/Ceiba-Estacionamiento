package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.unitarias;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.excepciones.EstacionamientoException;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.TipoVehiculoEnum;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.Vigilante;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.repositorio.RepositorioRegistroParqueo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.testdatabuilder.RegistroParqueoTestDataBuilder;

@SpringBootTest
@Transactional
@RunWith(SpringRunner.class)
public class VigilanteTest {

	@Mock
	private RepositorioRegistroParqueo repositorioRegistroParqueo;
	@InjectMocks
	private Vigilante vigilante;

	@Test
	public void diaNoHabilTest() {
		// Arrange
		RegistroParqueo registroParqueo = new RegistroParqueoTestDataBuilder(
				RegistroParqueoTestDataBuilder.SIN_SALIDA_FACTURADA).conPlaca("AKY12A").construirRegistroParqueo();
		try {
			// Act
			vigilante.validarDiaHabil(registroParqueo);
			fail();
		} catch (EstacionamientoException excepcion) {
			// Assert
			assertEquals(Vigilante.DIA_NO_HABIL, excepcion.getMessage());
		}
	}

	@Test
	public void sinEspacioParaMotosTest() {
		// Arrange
		RegistroParqueo registroParqueo = new RegistroParqueoTestDataBuilder(
				RegistroParqueoTestDataBuilder.SIN_SALIDA_FACTURADA).construirRegistroParqueo();

		when(repositorioRegistroParqueo.cantidadVehiculosPorTipo(registroParqueo.getTipoVehiculo().name()))
				.thenReturn(Vigilante.CUPO_MOTOS);
		try {
			// Act
			vigilante.validarCupo(registroParqueo);
			fail();
		} catch (EstacionamientoException excepcion) {
			// Assert
			assertEquals(Vigilante.MOTOS_SIN_CUPO, excepcion.getMessage());
		}
	}

	@Test
	public void sinEspacioParaCarrosTest() {
		// Arrange
		RegistroParqueo registroParqueo = new RegistroParqueoTestDataBuilder(
				RegistroParqueoTestDataBuilder.SIN_SALIDA_FACTURADA).conTipoVehiculo(TipoVehiculoEnum.CARRO)
						.construirRegistroParqueo();

		when(repositorioRegistroParqueo.cantidadVehiculosPorTipo(registroParqueo.getTipoVehiculo().name()))
				.thenReturn(Vigilante.CUPO_CARROS);
		try {
			// Act
			vigilante.validarCupo(registroParqueo);
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
		double valorFacturado = vigilante.calcularValorFacturado(fechaIngreso, fechaSalida, TipoVehiculoEnum.MOTO, 'S');
		
		// Assert
		assertTrue(11500 == valorFacturado);
	}
	
	@Test
	public void calcularValorCarroTest() {
		// Arrange
		Calendar fechaIngreso = new GregorianCalendar(2019, Calendar.JUNE, 14, 13, 13);
		Calendar fechaSalida  = new GregorianCalendar(2019, Calendar.JUNE, 15, 15, 27);
		
		// Act
		double valorFacturado = vigilante.calcularValorFacturado(fechaIngreso, fechaSalida, TipoVehiculoEnum.CARRO, 'N');
		
		// Assert
		assertTrue(11000 == valorFacturado);
	}

	@Test
	public void obtenerRegistroCalculadoTest() {
		// Arrange
		Calendar fechaIngreso = new GregorianCalendar(2019, Calendar.JUNE, 13, 13, 13);
		Calendar fechaSalida  = new GregorianCalendar(2019, Calendar.JUNE, 15, 15, 15);
		
		RegistroParqueo registroParqueo = new RegistroParqueoTestDataBuilder(
				RegistroParqueoTestDataBuilder.SIN_SALIDA_FACTURADA).conFechaIngreso(fechaIngreso)
					.conFechaSalida(fechaSalida).construirRegistroParqueo();		
		// Act
		RegistroParqueo registroCalculado = vigilante.obtenerRegistroCalculado(registroParqueo);
		
		// Assert
		assertTrue(11500 == registroCalculado.getValorFacturado());
	}
}
