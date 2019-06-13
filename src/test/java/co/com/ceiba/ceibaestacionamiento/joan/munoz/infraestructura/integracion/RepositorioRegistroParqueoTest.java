package co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.fabrica.DominioFactory;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.TipoVehiculoEnum;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.entidades.RegistroParqueoEntity;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.repositorio.RepositorioRegistroParqueo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.testdatabuilder.RegistroParqueoTestDataBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class RepositorioRegistroParqueoTest {

	@Autowired
	private RepositorioRegistroParqueo repositorioRegistroParqueo;
	@Autowired
	private DominioFactory dominioFactory;

	@Before
	public void setUp() {
		repositorioRegistroParqueo.deleteAll();
	}

	@Test
	public void cantidadVehiculosPorTipoTest() {
		// Arrange
		RegistroParqueoEntity registroParqueoAfuera = dominioFactory.convertiraDominioEntidad(
				new RegistroParqueoTestDataBuilder(RegistroParqueoTestDataBuilder.CON_SALIDA_FACTURADA)
						.construirRegistroParqueo());
		RegistroParqueoEntity registroParqueoAdentro = dominioFactory.convertiraDominioEntidad(
				new RegistroParqueoTestDataBuilder(RegistroParqueoTestDataBuilder.SIN_SALIDA_FACTURADA)
						.construirRegistroParqueo());

		repositorioRegistroParqueo.saveAndFlush(registroParqueoAfuera);
		repositorioRegistroParqueo.saveAndFlush(registroParqueoAdentro);

		// Act
		int cantidadAdentro = repositorioRegistroParqueo.cantidadVehiculosPorTipo(TipoVehiculoEnum.MOTO.name());

		// Assert
		assertFalse(cantidadAdentro == 2);
		assertTrue(cantidadAdentro == 1);
	}

	@Test
	public void buscarVehiculoIngresadoTest() {
		// Arrange
		RegistroParqueoEntity registroParqueo = dominioFactory.convertiraDominioEntidad(
				new RegistroParqueoTestDataBuilder(RegistroParqueoTestDataBuilder.SIN_SALIDA_FACTURADA)
						.construirRegistroParqueo());

		repositorioRegistroParqueo.saveAndFlush(registroParqueo);

		// Act
		RegistroParqueoEntity registroParqueoAlmacenado = repositorioRegistroParqueo
				.buscarVehiculoIngresado(registroParqueo.getPlaca());

		// Assert
		assertNotNull(registroParqueoAlmacenado.getId());
		assertEquals(registroParqueoAlmacenado.getFechaIngreso(), registroParqueo.getFechaIngreso());
		assertNull(registroParqueoAlmacenado.getFechaSalida());
		assertEquals(registroParqueoAlmacenado.getTipoVehiculo(), registroParqueo.getTipoVehiculo());
		assertTrue(registroParqueoAlmacenado.getEsMotoPesada() == registroParqueo.getEsMotoPesada());
		assertEquals(registroParqueoAlmacenado.getPlaca(), registroParqueo.getPlaca());
		assertTrue(registroParqueoAlmacenado.getValorFacturado() == 0.0);
	}
}
