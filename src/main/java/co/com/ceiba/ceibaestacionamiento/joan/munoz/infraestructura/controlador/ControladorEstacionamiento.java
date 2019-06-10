package co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.IServicioRegistrarVehiculo;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.modelo.RegistroVehiculo;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/api")
public class ControladorEstacionamiento {
	
	@Autowired
	private IServicioRegistrarVehiculo servicioRegistrarVehiculo; 
	
	@PostMapping("/ingresarVehiculo")
	public RegistroVehiculo ingresarVehiculo(@RequestBody RegistroVehiculo registroVehiculo) {
		
		 return  servicioRegistrarVehiculo.ingresarVehiculo(registroVehiculo);
	}
	
}
