package co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion;

import java.util.Calendar;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.entidades.EntidadRegistroVehiculo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.excepciones.ExcepcionEstacionamiento;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.EnumTipoVehiculo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroVehiculo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.repositorio.IRepositorioRegistroVehiculo;

@Service
public class ServicioRegistrarVehiculo implements IServicioRegistrarVehiculo {

	public static final String MOTOS_SIN_CUPO  = "Actualmente no hay espacio disponible para motos.";
	public static final String CARROS_SIN_CUPO = "Actualmente no hay espacio disponible para carros.";
	public static final String DIA_NO_HABIL = "El vehículo no puede ingresar porque no es un día hábil.";
	
	@Autowired
	private IRepositorioRegistroVehiculo repositorioRegistroVehiculo;
	ModelMapper modelMapper = new ModelMapper();	

	@Override
	public RegistroVehiculo ingresarVehiculo(RegistroVehiculo registroVehiculo) {
		
		realizarValidaciones(registroVehiculo);
		
		EntidadRegistroVehiculo entidadRegistroVehiculo = modelMapper.map(registroVehiculo, EntidadRegistroVehiculo.class);
		EntidadRegistroVehiculo registroAlmacenado = repositorioRegistroVehiculo.saveAndFlush(entidadRegistroVehiculo);
		return modelMapper.map(registroAlmacenado, RegistroVehiculo.class);
	}
	
	private void realizarValidaciones(RegistroVehiculo registroVehiculo) {
		
		String placa = registroVehiculo.getPlaca();
		if(placa.charAt(0) == 'A') {
			Calendar fechaActual = Calendar.getInstance();
			if(fechaActual.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY &&
					fechaActual.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
				throw new ExcepcionEstacionamiento( DIA_NO_HABIL );
		}
		
		EnumTipoVehiculo tipo = registroVehiculo.getTipoVehiculo();
		int cantidadVehiculos = repositorioRegistroVehiculo.cantidadVehiculosPorTipo(tipo.toString());	
		System.out.println("Cantidad: "+cantidadVehiculos);
		if(tipo.equals(EnumTipoVehiculo.Moto) && cantidadVehiculos == 2) // TODO Modificar 2 por 10
			throw new ExcepcionEstacionamiento( MOTOS_SIN_CUPO );
		if(tipo.equals(EnumTipoVehiculo.Carro) && cantidadVehiculos == 20)
			throw new ExcepcionEstacionamiento(CARROS_SIN_CUPO);
	}

}
