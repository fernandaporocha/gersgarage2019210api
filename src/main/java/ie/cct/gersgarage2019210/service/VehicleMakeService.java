package ie.cct.gersgarage2019210.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gersgarage2019210.dto.VehicleMakeDTO;
import ie.cct.gersgarage2019210.dto.VehicleModelDTO;
import ie.cct.gersgarage2019210.model.VehicleMake;
import ie.cct.gersgarage2019210.repository.VehicleMakeRepository;

@Service
public class VehicleMakeService {
	@Autowired
	private VehicleMakeRepository repository;
	@Autowired 
	VehicleModelService vehicleModelService;
	@Autowired
	VehicleTypeService vehicleTypeService;
	
	public void create(VehicleMakeDTO dto) {
		VehicleMake vehicleMake = new VehicleMake(null, dto.getName(), vehicleTypeService.find(dto.getVehicleTypeId()));
		VehicleMake savedMake = repository.save(vehicleMake);
		vehicleModelService.create(new VehicleModelDTO(null, "Other", savedMake.getId(), savedMake.getName()));
	}
	
	public List<VehicleMake> findAll(){
		return (List<VehicleMake>) repository.findAll();
	}
	
	public VehicleMake find(Integer id) {
		Optional<VehicleMake> vehicleMake = (Optional<VehicleMake>) repository.findById(id);
		return vehicleMake.get();
	}
	
	public void update(VehicleMakeDTO dto) {
		VehicleMake vehicleMake = find(dto.getId());
		vehicleMake.setName(dto.getName());
		vehicleMake.setType(vehicleTypeService.find(dto.getVehicleTypeId()));
		repository.save(vehicleMake);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}

}
