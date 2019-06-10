package co.com.ceiba.ceibaestacionamiento.joan.munoz.repositorio;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.entidades.EntidadRegistroVehiculo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.EnumTipoVehiculo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.repositorio.IRepositorioRegistroVehiculo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IRepositorioRegistroVehiculoTest {
	
	@Autowired
	private IRepositorioRegistroVehiculo repositorioRegistroVehiculo;
	
	@Test
	public void cantidadVehiculosPorTipoTest() {

		// Arrange
		EntidadRegistroVehiculo registroVehiculoAfuera = new EntidadRegistroVehiculo();
		registroVehiculoAfuera.setId(10L);
		registroVehiculoAfuera.setFechaIngreso(new Date());
		registroVehiculoAfuera.setFechaSalida(new Date());
		registroVehiculoAfuera.setPlaca("VKY61C");
		registroVehiculoAfuera.setTipoVehiculo(EnumTipoVehiculo.Moto.toString());
		registroVehiculoAfuera.setValorFacturado(500);
		
		EntidadRegistroVehiculo registroVehiculoAdentro = new EntidadRegistroVehiculo();
		registroVehiculoAdentro.setId(10L);
		registroVehiculoAdentro.setFechaIngreso(new Date());
		registroVehiculoAdentro.setPlaca("PUR21A");
		registroVehiculoAdentro.setTipoVehiculo(EnumTipoVehiculo.Moto.toString());			
		
		repositorioRegistroVehiculo.saveAndFlush(registroVehiculoAfuera);
		repositorioRegistroVehiculo.saveAndFlush(registroVehiculoAdentro);
		
		// Act
		int cantidad = repositorioRegistroVehiculo.cantidadVehiculosPorTipo(EnumTipoVehiculo.Moto.toString());
		
		// Assert
		assertTrue(cantidad == 1);
	}
}
