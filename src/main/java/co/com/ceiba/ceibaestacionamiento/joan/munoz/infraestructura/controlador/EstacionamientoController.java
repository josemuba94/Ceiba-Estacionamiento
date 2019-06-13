package co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.dtos.RegistroParqueoDTO;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.servicios.CalcularSalidaService;
import co.com.ceiba.ceibaestacionamiento.joan.munoz.aplicacion.servicios.RegistrarVehiculoService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" })
@RequestMapping("/api")
public class EstacionamientoController {

	public static final String URL_REGISTRAR_INGRESO = "/registrarIngreso";
	public static final String URL_CALCULAR_SALIDA   = "/calcularSalida/{placa}";
	public static final String URL_REGISTRAR_SALIDA  = "/registrarSalida";
	
	@Autowired
	private RegistrarVehiculoService vigilanteIngresarService;
	@Autowired
	private CalcularSalidaService vigilanteCalcularService;

	@PostMapping(URL_REGISTRAR_INGRESO)
	public RegistroParqueoDTO registrarIngresoVehiculo(@RequestBody RegistroParqueoDTO registroParqueoDTO) {
		return vigilanteIngresarService.registrarIngresoVehiculo(registroParqueoDTO);
	}
	
	@GetMapping(URL_CALCULAR_SALIDA)
	public RegistroParqueoDTO calcularSalida(@PathVariable String placa) {
		return vigilanteCalcularService.calcularSalida(placa);	
	}
}