package co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.infraestructura.entidades.RegistroParqueoEntity;

@Repository
public interface RepositorioRegistroParqueoJPA extends JpaRepository<RegistroParqueoEntity, Long> {

	@Query(value = "select count(r.tipo_Vehiculo) FROM Registros_Parqueo r where r.fecha_Salida is null and r.tipo_Vehiculo=?1 ", nativeQuery = true)
	public int cantidadVehiculosPorTipo(String tipo);
	
	@Query(value = "select * FROM Registros_Parqueo r where r.fecha_Salida is null and r.placa=?1 ", nativeQuery = true)
	public RegistroParqueoEntity buscarVehiculoIngresado(String placa);
}