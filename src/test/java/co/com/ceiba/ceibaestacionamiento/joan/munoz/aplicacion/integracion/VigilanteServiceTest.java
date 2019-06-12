package co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.integracion;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos.RegistroParqueoDTO;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.fabrica.AplicacionFactory;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.servicios.RegistrarVehiculoService;
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
	private RepositorioRegistroParqueo repositorioRegistroParqueo;
	@Autowired
	private AplicacionFactory aplicacionFactory;

	@After
	public void tearDown() {
		repositorioRegistroParqueo.deleteAll();
	}

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
}
