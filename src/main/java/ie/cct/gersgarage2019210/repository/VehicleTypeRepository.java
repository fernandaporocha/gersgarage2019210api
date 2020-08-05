package ie.cct.gersgarage2019210.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.cct.gersgarage2019210.model.VehicleType;

@Repository
public interface VehicleTypeRepository extends CrudRepository<VehicleType, Integer>{
	
}
