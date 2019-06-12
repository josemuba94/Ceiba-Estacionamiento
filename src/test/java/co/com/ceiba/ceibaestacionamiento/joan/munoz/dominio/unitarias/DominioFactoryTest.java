package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.unitarias;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.fabrica.DominioFactory;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroParqueo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.TipoVehiculoEnum;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.entidades.RegistroParqueoEntity;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.testdatabuilder.RegistroParqueoTestDataBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DominioFactoryTest {

	@Autowired
	private DominioFactory dominioFactory;

	@Test
	public void convertiraDominioEntidadTest() {

		// Arrange
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		RegistroParqueo registroParqueo = new RegistroParqueoTestDataBuilder(
				RegistroParqueoTestDataBuilder.CON_SALIDA_FACTURADA).construirRegistroParqueo();
		String fechaIngresada = formatoFecha.format(registroParqueo.getFechaIngreso().getTime());

		// Act
		RegistroParqueoEntity registroParqueoEntity = dominioFactory.convertiraDominioEntidad(registroParqueo);
		String fechaObtenida = formatoFecha.format(registroParqueoEntity.getFechaIngreso().getTime());

		// Assert
		assertTrue(registroParqueoEntity.getId().equals(registroParqueo.getId()));
		assertEquals(fechaIngresada, fechaObtenida);
		assertEquals(registroParqueoEntity.getFechaSalida(), registroParqueo.getFechaSalida());
		assertEquals(registroParqueoEntity.getTipoVehiculo(), registroParqueo.getTipoVehiculo().name());
		assertTrue(registroParqueoEntity.getEsMotoPesada() == registroParqueo.getEsMotoPesada());
		assertEquals(registroParqueoEntity.getPlaca(), registroParqueo.getPlaca());
		assertTrue(registroParqueoEntity.getValorFacturado() == registroParqueo.getValorFacturado());
	}

	@Test
	public void convertirEntidadDominioTest() {

		// Arrange
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		RegistroParqueoEntity registroParqueoEntity = new RegistroParqueoEntity();
		registroParqueoEntity.setId(321L);
		registroParqueoEntity.setFechaIngreso(new GregorianCalendar(2019, Calendar.JUNE, 10, 12, 10));
		registroParqueoEntity.setFechaSalida(new GregorianCalendar(2019, Calendar.JUNE, 10, 12, 50));
		registroParqueoEntity.setTipoVehiculo(TipoVehiculoEnum.CARRO.name());
		registroParqueoEntity.setEsMotoPesada('N');
		registroParqueoEntity.setPlaca("LVS56");
		registroParqueoEntity.setValorFacturado(1000);
		String fechaIngresada = formatoFecha.format(registroParqueoEntity.getFechaIngreso().getTime());

		// Act
		RegistroParqueo registroParqueo = dominioFactory.convertirEntidadDominio(registroParqueoEntity);
		String fechaObtenida = formatoFecha.format(registroParqueo.getFechaIngreso().getTime());

		// Assert
		assertTrue(registroParqueoEntity.getId().equals(registroParqueo.getId()));
		assertEquals(fechaIngresada, fechaObtenida);
		assertEquals(registroParqueoEntity.getFechaSalida(), registroParqueo.getFechaSalida());
		assertEquals(registroParqueoEntity.getTipoVehiculo(), registroParqueo.getTipoVehiculo().name());
		assertTrue(registroParqueoEntity.getEsMotoPesada() == registroParqueo.getEsMotoPesada());
		assertEquals(registroParqueoEntity.getPlaca(), registroParqueo.getPlaca());
		assertTrue(registroParqueoEntity.getValorFacturado() == registroParqueo.getValorFacturado());
	}

}
