package ie.cct.gersgarage2019210.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.cct.gersgarage2019210.model.VehicleModel;

@Repository
public interface VehicleModelRepository extends CrudRepository<VehicleModel, Integer>{
	
	List<VehicleModel> findByMakeId(Integer vehicleMakeId);

}
