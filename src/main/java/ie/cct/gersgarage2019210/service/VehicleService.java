package ie.cct.gersgarage2019210.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gersgarage2019210.dto.VehicleDTO;
import ie.cct.gersgarage2019210.model.Vehicle;
import ie.cct.gersgarage2019210.repository.VehicleRepository;

@Service
public class VehicleService {
	@Autowired
	private VehicleRepository repository;
	@Autowired
	private VehicleEngineService vehicleEngineService;
	@Autowired
	private VehicleTypeService vehicleTypeService;
	@Autowired
	private VehicleModelService vehicleModelService;
	@Autowired
	private UserService userService;
	
	public void create(VehicleDTO dto) {
		Vehicle vehicle = new Vehicle(
				null, 
				vehicleTypeService.find(dto.getTypeId()), 
				vehicleModelService.find(dto.getModelId()), 
				vehicleEngineService.find(dto.getEngineId()), 
				dto.getLicence(), 
				dto.getYear(),
				dto.getCustomerId()==null?null:userService.find(dto.getCustomerId()));
		repository.save(vehicle);
	}
	
	public List<Vehicle> findAll(){
		return (List<Vehicle>) repository.findAll();
	}
	
	public Vehicle find(Integer id) {
		Optional<Vehicle> item = (Optional<Vehicle>) repository.findById(id);
		return item.get();
	}
	
	public void update(VehicleDTO dto) {
		Vehicle vehicle = find(dto.getId());
		vehicle.setEngine(vehicleEngineService.find(dto.getEngineId()));
		vehicle.setModel(vehicleModelService.find(dto.getModelId()));
		vehicle.setType(vehicleTypeService.find(dto.getTypeId()));
		vehicle.setLicence(dto.getLicence());
		vehicle.setYear(dto.getYear());
		vehicle.setCustomer(userService.find(dto.getCustomerId()));
		repository.save(vehicle);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
}
