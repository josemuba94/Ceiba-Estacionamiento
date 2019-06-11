package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.integracion;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.IVigilante;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
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

	@After
	public void tearDown() {
		repositorioRegistroParqueo.deleteAll();
	}
	
	@Test
	public void registrarIngresoVehiculo() {
		
		// Arrange
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		RegistroParqueo registroParqueo = new RegistroParqueoTestDataBuilder(
				RegistroParqueoTestDataBuilder.SIN_SALIDA_FACTURADA)
				.construirRegistroParqueo();	
		String fechaIngresada = dateFormat.format(registroParqueo.getFechaIngreso().getTime());
		
		// Act
		RegistroParqueo registroParqueoAlmacenado = vigilante.registrarIngresoVehiculo(registroParqueo);
		String fechaObtenida = dateFormat.format(registroParqueoAlmacenado.getFechaIngreso().getTime());	
		
		// Assert	
		assertNotNull(registroParqueoAlmacenado.getId());
		assertEquals(fechaIngresada, fechaObtenida);
		assertNull(registroParqueoAlmacenado.getFechaSalida());
		assertEquals(registroParqueoAlmacenado.getTipoVehiculo().name(), registroParqueo.getTipoVehiculo().name());
		assertTrue(registroParqueoAlmacenado.getEsMotoPesada() == registroParqueo.getEsMotoPesada());		
		assertEquals(registroParqueoAlmacenado.getPlaca(), registroParqueo.getPlaca());
		assertTrue(registroParqueoAlmacenado.getValorFacturado() == 0.0);
	}
}
