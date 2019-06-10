package co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.ceibaestacionamiento.joan.munoz.dominio.entidades.RegistroVehiculo;

@Repository
public interface IRepositorioRegistroVehiculo extends JpaRepository<RegistroVehiculo, Long> {

	
}
