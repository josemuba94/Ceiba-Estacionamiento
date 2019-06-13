package co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.integracion;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.json.JSONObject;
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

	public static final MediaType APLICACION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private EstacionamientoController estacionamientoController;
	@Autowired
	private RepositorioRegistroParqueo repositorioRegistroParqueo;
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
			String resultado = mockMvc
					.perform(post("http://localhost:8080/api" + EstacionamientoController.URL_REGISTRAR_INGRESO)
							.contentType(APLICACION_JSON_UTF8).content(registroParqueoJSON))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			
			JSONObject json = new JSONObject(resultado);
			
			// Assert
			assertEquals("BKY61C", json.getString("placa"));
			
		} catch (Exception e) {
			throw new EstacionamientoException(e.getMessage());
		}
	}

	@Test
	public void calcularSalidaTest() {
		// Arrange
		String placa = "LVS56A";

		try {
			// Act
			String resultado = mockMvc
					.perform(get("http://localhost:8080/api" + EstacionamientoController.URL_CALCULAR_SALIDA, placa))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			JSONObject json = new JSONObject(resultado);
			
			// Assert
			assertEquals(placa, json.getString("placa"));

		} catch (Exception e) {
			throw new EstacionamientoException(e.getMessage());
		}
	}
}
