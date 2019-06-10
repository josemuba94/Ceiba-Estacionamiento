package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.entidades.EntidadRegistroVehiculo;

@Repository
public interface IRepositorioRegistroVehiculo extends JpaRepository<EntidadRegistroVehiculo, Long> {

	@Query(value = "select count(r.tipo_Vehiculo) FROM Registros_Vehiculo r where r.fecha_Salida is null and r.tipo_Vehiculo=?1 ", nativeQuery = true)
	public int cantidadVehiculosPorTipo(String tipo);
}
