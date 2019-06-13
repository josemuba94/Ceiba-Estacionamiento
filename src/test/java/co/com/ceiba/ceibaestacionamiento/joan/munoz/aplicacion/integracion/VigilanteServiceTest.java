package co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.integracion;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos.RegistroParqueoDTO;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.fabrica.AplicacionFactory;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.servicios.CalcularSalidaService;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.servicios.RegistrarVehiculoService;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.fabrica.DominioFactory;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.repositorio.RepositorioRegistroParqueo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.testdatabuilder.RegistroParqueoTestDataBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class VigilanteServiceTest {

	@Autowired
	private RegistrarVehiculoService registrarVehiculoService;
	@Autowired
	private CalcularSalidaService calcularSalidaService;
	@Autowired
	private RepositorioRegistroParqueo repositorioRegistroParqueo;
	@Autowired
	private AplicacionFactory aplicacionFactory;
	@Autowired
	private DominioFactory dominioFactory;
	
	@Test
	public void registrarIngresoVehiculoTest() {

		// Arrange
		RegistroParqueo registroParqueo = new RegistroParqueoTestDataBuilder(
				RegistroParqueoTestDataBuilder.SIN_SALIDA_FACTURADA).conFechaIngreso(null).construirRegistroParqueo();

		// Act
		RegistroParqueoDTO registroParqueoAlmacenado = registrarVehiculoService
				.registrarIngresoVehiculo(aplicacionFactory.convertirDominioDTO(registroParqueo));

		// Assert
		assertNotNull(registroParqueoAlmacenado.getId());
		assertNotNull(registroParqueoAlmacenado.getFechaIngreso());
		assertNull(registroParqueoAlmacenado.getFechaSalida());
		assertEquals(registroParqueoAlmacenado.getTipoVehiculo(), registroParqueo.getTipoVehiculo().name());
		assertTrue(registroParqueoAlmacenado.getEsMotoPesada() == registroParqueo.getEsMotoPesada());
		assertEquals(registroParqueoAlmacenado.getPlaca(), registroParqueo.getPlaca());
		assertTrue(registroParqueoAlmacenado.getValorFacturado() == 0.0);
	}

	@Test
	public void calcularSalidaTest() {
		// Arrange
		RegistroParqueo registroParqueo = dominioFactory
				.convertirEntidadDominio(repositorioRegistroParqueo.buscarVehiculoIngresado("LVS56A"));
		
		final long cuatroHorasYmedia = 16200000;
		Calendar fechaIngreso = new GregorianCalendar();
		fechaIngreso.setTimeInMillis((new Date().getTime()) - cuatroHorasYmedia);

		registroParqueo.setFechaIngreso(fechaIngreso);
		repositorioRegistroParqueo.saveAndFlush(dominioFactory.convertiraDominioEntidad(registroParqueo));

		// Act
		RegistroParqueoDTO registroParqueoCalculado = calcularSalidaService.calcularSalida(registroParqueo.getPlaca());
		
		// Assert
		assertNotNull(registroParqueoCalculado.getId());
		assertNotNull(registroParqueoCalculado.getFechaSalida());
		assertEquals(registroParqueoCalculado.getPlaca(), registroParqueo.getPlaca());
		assertTrue(4500 == registroParqueoCalculado.getValorFacturado());
	}
}
