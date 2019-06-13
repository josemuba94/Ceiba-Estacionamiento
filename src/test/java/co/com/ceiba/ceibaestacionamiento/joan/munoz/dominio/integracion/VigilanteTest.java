package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.integracion;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.excepciones.EstacionamientoException;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.IVigilante;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.Vigilante;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.entidades.RegistroParqueoEntity;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.repositorio.RepositorioRegistroParqueo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.testdatabuilder.RegistroParqueoTestDataBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class VigilanteTest {

	@Autowired
	private RepositorioRegistroParqueo repositorioRegistroParqueo;
	@Autowired
	private IVigilante vigilante;	

	@Test
	public void registrarIngresoVehiculoTest() {
		// Arrange
		RegistroParqueo registroParqueo = new RegistroParqueoTestDataBuilder(
				RegistroParqueoTestDataBuilder.SIN_SALIDA_FACTURADA).conFechaIngreso(null).construirRegistroParqueo();

		// Act
		RegistroParqueo registroParqueoAlmacenado = vigilante.registrarIngresoVehiculo(registroParqueo);

		// Assert
		assertNotNull(registroParqueoAlmacenado.getId());
		assertNotNull(registroParqueoAlmacenado.getFechaIngreso());
		assertNull(registroParqueoAlmacenado.getFechaSalida());
		assertEquals(registroParqueoAlmacenado.getTipoVehiculo().name(), registroParqueo.getTipoVehiculo().name());
		assertTrue(registroParqueoAlmacenado.getEsMotoPesada() == registroParqueo.getEsMotoPesada());
		assertEquals(registroParqueoAlmacenado.getPlaca(), registroParqueo.getPlaca());
		assertTrue(registroParqueoAlmacenado.getValorFacturado() == 0.0);
	}

	@Test
	public void vehiculoNoIngresadoTest() {
		// Arrange
		String placa = "ABC123";
		try {
			// Act
			vigilante.calcularSalida(placa);		
			fail();
		} catch (EstacionamientoException excepcion) {
			// Assert
			assertEquals(Vigilante.VEHICULO_NO_INGRESADO, excepcion.getMessage());
		}
	}

	@Test
	@Sql("/import.sql")
	public void calcularSalidaTest() {
		// Arrange
		final long cuatroHorasYmedia = 16200000;
		
		RegistroParqueoEntity registroParqueoEntity = repositorioRegistroParqueo
				.buscarVehiculoIngresado("LVS56A");

		Calendar fechaIngreso = new GregorianCalendar();
		fechaIngreso.setTimeInMillis((new Date().getTime()) - cuatroHorasYmedia);
		
		registroParqueoEntity.setFechaIngreso(fechaIngreso);		
		repositorioRegistroParqueo.saveAndFlush(registroParqueoEntity);
		
		// Act
		RegistroParqueo registroParqueoCalculado = vigilante.calcularSalida(registroParqueoEntity.getPlaca());

		// Assert
		assertNotNull(registroParqueoCalculado.getId());
		assertNotNull(registroParqueoCalculado.getFechaSalida());
		assertEquals(registroParqueoCalculado.getPlaca(), registroParqueoEntity.getPlaca());
		assertTrue(4500 == registroParqueoCalculado.getValorFacturado());
	}
}
