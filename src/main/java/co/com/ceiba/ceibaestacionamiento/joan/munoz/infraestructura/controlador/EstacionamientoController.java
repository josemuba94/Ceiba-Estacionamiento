package co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos.RegistroParqueoDTO;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.servicios.RegistrarVehiculoService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/api")
public class EstacionamientoController {

	public static final String URL_REGISTRAR_VEHICULO = "/registrarIngresoVehiculo";
	
	@Autowired
	private RegistrarVehiculoService vigilanteService;

	@PostMapping(URL_REGISTRAR_VEHICULO)
	public RegistroParqueoDTO registrarIngresoVehiculo(@RequestBody RegistroParqueoDTO registroParqueoDTO) {
		return vigilanteService.registrarIngresoVehiculo(registroParqueoDTO);
	}

}