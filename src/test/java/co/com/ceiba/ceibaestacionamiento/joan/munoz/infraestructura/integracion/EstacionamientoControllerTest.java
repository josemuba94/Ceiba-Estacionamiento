package co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.integracion;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.excepciones.EstacionamientoException;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.controlador.EstacionamientoController;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.repositorio.RepositorioRegistroParqueo;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class EstacionamientoControllerTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Autowired
	private RepositorioRegistroParqueo repositorioRegistroParqueo;
	@Autowired
	private EstacionamientoController estacionamientoController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(estacionamientoController).build();
	}

	@After
	public void tearDown() {
		repositorioRegistroParqueo.deleteAll();
	}

	@Test
	public void registrarIngresoVehiculoTest() {
		// Arrange
		String registroParqueoJSON = "{ \"tipoVehiculo\": \"MOTO\", \"esMotoPesada\": \"N\", \"placa\": \"BKY61C\" }";

		try {
			// Act
			mockMvc.perform(post("http://localhost:8080/api/registrarIngresoVehiculo/")
					.contentType(APPLICATION_JSON_UTF8).content(registroParqueoJSON))
					// Assert
					.andExpect(status().isOk());
		} catch (Exception e) {
			throw new EstacionamientoException(e.getMessage());
		}		
	}
}
