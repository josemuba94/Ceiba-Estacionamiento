package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.unitarias;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

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
			vigilante.realizarValidacionesIngreso(registroParqueo);
			fail();
		} catch (EstacionamientoException e) {
			// Assert
			assertEquals(Vigilante.DIA_NO_HABIL, e.getMessage());
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
			vigilante.realizarValidacionesIngreso(registroParqueo);
			fail();
		} catch (EstacionamientoException e) {
			// Assert
			assertEquals(Vigilante.MOTOS_SIN_CUPO, e.getMessage());
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
			vigilante.realizarValidacionesIngreso(registroParqueo);
			fail();
		} catch (EstacionamientoException e) {
			// Assert
			assertEquals(Vigilante.CARROS_SIN_CUPO, e.getMessage());
		}
	}
}
