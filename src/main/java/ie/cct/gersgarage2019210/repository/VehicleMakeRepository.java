package ie.cct.gersgarage2019210.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.cct.gersgarage2019210.model.VehicleMake;

@Repository
public interface VehicleMakeRepository extends CrudRepository<VehicleMake, Integer>{

}
