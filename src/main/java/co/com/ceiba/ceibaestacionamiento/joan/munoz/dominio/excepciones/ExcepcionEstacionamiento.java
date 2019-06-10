package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.excepciones;

public class ExcepcionEstacionamiento extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ExcepcionEstacionamiento(String mensaje) {
		super(mensaje);
	}
}
