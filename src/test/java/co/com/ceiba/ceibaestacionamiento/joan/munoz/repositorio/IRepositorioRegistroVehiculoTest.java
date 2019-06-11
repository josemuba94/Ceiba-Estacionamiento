package co.com.ceiba.ceibaestacionamiento.joan.munoz.repositorio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.TipoVehiculoEnum;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.fabrica.FabricaRegistroParqueoTest;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.entidades.RegistroParqueoEntity;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.repositorio.IRepositorioRegistroParqueo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IRepositorioRegistroVehiculoTest {
	
	@Autowired
	private IRepositorioRegistroParqueo repositorioRegistroVehiculo;
	ModelMapper modelMapper = new ModelMapper();
	
	private static final boolean CON_SALIDA_FACTURADA = true;
	private static final boolean SIN_SALIDA_FACTURADA = false;
	
	@Test
	public void cantidadVehiculosPorTipoTest() {

		// Arrange
		FabricaRegistroParqueoTest fabricaRegistroVehiculoTestAfuera = new FabricaRegistroParqueoTest(CON_SALIDA_FACTURADA);
		FabricaRegistroParqueoTest fabricaRegistroVehiculoTestAdentro = new FabricaRegistroParqueoTest(SIN_SALIDA_FACTURADA);
		
		RegistroParqueoEntity registroVehiculoAfuera  = modelMapper.map(
				fabricaRegistroVehiculoTestAfuera.construirRegistroVehiculo(), RegistroParqueoEntity.class);
		RegistroParqueoEntity registroVehiculoAdentro = modelMapper.map(
				fabricaRegistroVehiculoTestAdentro.construirRegistroVehiculo(), RegistroParqueoEntity.class);		
		
		repositorioRegistroVehiculo.saveAndFlush(registroVehiculoAfuera);
		repositorioRegistroVehiculo.saveAndFlush(registroVehiculoAdentro);
		
		// Act
		int cantidadAdentro = repositorioRegistroVehiculo.cantidadVehiculosPorTipo(TipoVehiculoEnum.MOTO.toString());

		// Assert
		assertFalse(cantidadAdentro == 0);
		assertFalse(cantidadAdentro == 2);
		assertTrue (cantidadAdentro == 1);
	}
}
