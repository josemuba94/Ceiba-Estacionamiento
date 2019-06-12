package co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.integracion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
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

	@After
	public void tearDown() {
		repositorioRegistroParqueo.deleteAll();
	}

	@Test
	public void cantidadVehiculosPorTipoTest() {

		// Arrange
		RegistroParqueoTestDataBuilder registroParqueoTestAfuera = new RegistroParqueoTestDataBuilder(
				RegistroParqueoTestDataBuilder.CON_SALIDA_FACTURADA);
		RegistroParqueoTestDataBuilder registroParqueoTestAdentro = new RegistroParqueoTestDataBuilder(
				RegistroParqueoTestDataBuilder.SIN_SALIDA_FACTURADA);

		RegistroParqueoEntity registroParqueoAfuera = dominioFactory
				.convertiraDominioEntidad(registroParqueoTestAfuera.construirRegistroParqueo());
		RegistroParqueoEntity registroParqueoAdentro = dominioFactory
				.convertiraDominioEntidad(registroParqueoTestAdentro.construirRegistroParqueo());

		repositorioRegistroParqueo.saveAndFlush(registroParqueoAfuera);
		repositorioRegistroParqueo.saveAndFlush(registroParqueoAdentro);

		// Act
		int cantidadAdentro = repositorioRegistroParqueo.cantidadVehiculosPorTipo(TipoVehiculoEnum.MOTO.name());

		// Assert
		assertFalse(cantidadAdentro == 2);
		assertTrue(cantidadAdentro == 1);
	}
}
