package ie.cct.gersgarage2019210.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.cct.gersgarage2019210.model.Vehicle;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Integer>{
	
	List<Vehicle> findAllByCustomerId(Integer customerId);
}
