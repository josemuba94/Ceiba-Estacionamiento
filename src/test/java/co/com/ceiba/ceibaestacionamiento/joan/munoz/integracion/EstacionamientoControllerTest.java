package co.com.ceiba.ceibaestacionamiento.joan.munoz.integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.excepciones.EstacionamientoException;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.Vigilante;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.controlador.EstacionamientoController;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.repositorio.RepositorioRegistroH2;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.repositorio.RepositorioRegistroParqueoJPA;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class EstacionamientoControllerTest {

	public static final MediaType APLICACION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private EstacionamientoController estacionamientoController;
	@Autowired
	private Vigilante vigilante;
	@Autowired
	private RepositorioRegistroParqueoJPA repositorioRegistroParqueo;
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
	public void ingresarVehiculoTest() {
		// Arrange
		String registroParqueoJSON = "{ \"tipoVehiculo\": \"MOTO\", \"esMotoAltoCilindraje\": \"N\", \"placa\": \"BKY61C\",\r\n"
				+ "	\"fecha\": \"2019-06-13T16:22:36.204-0500\" }";
		try {
			// Act
			String resultado = mockMvc
					.perform(post("http://localhost:8080/api" + EstacionamientoController.URL_INGRESAR_VEHICULO)
							.contentType(APLICACION_JSON_UTF8).content(registroParqueoJSON))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			JSONObject json = new JSONObject(resultado);

			// Assert
			assertEquals("BKY61C", json.getString("placa"));

		} catch (Exception exception) {
			throw new EstacionamientoException(exception.getMessage());
		}
	}

	@Test
	public void vehiculoNoEncontradoTest() {
		// Arrange
		String placa = "#%$/(/";

		try {
			// Act
			vigilante.calcularSalida(placa);
			fail();
		} catch (EstacionamientoException excepcion) {
			// Assert
			assertEquals(excepcion.getMessage(), (RepositorioRegistroH2.VEHICULO_NO_INGRESADO));
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

		} catch (Exception exception) {
			throw new EstacionamientoException(exception.getMessage());
		}
	}

	@Test
	public void sacarVehiculoTest() {
		// Arrange
		String registroParqueoJSON = 
				"{\r\n" + 
				"  \"id\": 100,\r\n" + 
				"  \"fechaIngreso\": \"2019-06-01T12:19:21.204-0500\",\r\n" + 
				"  \"fechaSalida\": \"2019-06-03T14:22:14.621-0500\",\r\n" + 
				"  \"tipoVehiculo\": \"MOTO\",\r\n" + 
				"  \"esMotoAltoCilindraje\": \"S\",\r\n" + 
				"  \"placa\": \"LVS56A\",\r\n" + 
				"  \"valor\": 11500.0\r\n" + 
				"}";
		try {
			// Act
			String resultado = mockMvc
					.perform(put("http://localhost:8080/api" + EstacionamientoController.URL_SACAR_VEHICULO)
							.contentType(APLICACION_JSON_UTF8).content(registroParqueoJSON))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			JSONObject json = new JSONObject(resultado);

			// Assert
			assertEquals("LVS56A", json.getString("placa"));
			assertEquals(11500, json.getDouble("valor"), 0);

		} catch (Exception exception) {
			throw new EstacionamientoException(exception.getMessage());
		}
	}
}
