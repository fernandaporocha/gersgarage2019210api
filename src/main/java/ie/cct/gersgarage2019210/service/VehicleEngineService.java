package ie.cct.gersgarage2019210.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gersgarage2019210.dto.VehicleEngineDTO;
import ie.cct.gersgarage2019210.model.VehicleEngine;
import ie.cct.gersgarage2019210.repository.VehicleEngineRepository;

@Service
public class VehicleEngineService {
	@Autowired
	private VehicleEngineRepository repository;
	
	public void create(VehicleEngineDTO dto) {
		VehicleEngine vehicleEngine = new VehicleEngine(null, dto.getName());
		repository.save(vehicleEngine);
	}
	
	public List<VehicleEngine> findAll(){
		return (List<VehicleEngine>) repository.findAll();
	}
	
	public VehicleEngine find(Integer id) {
		Optional<VehicleEngine> vehicleEngine = (Optional<VehicleEngine>) repository.findById(id);
		return vehicleEngine.get();
	}
	
	public void update(VehicleEngineDTO dto) {
		VehicleEngine vehicleEngine = find(dto.getId());
		vehicleEngine.setName(dto.getName());
		repository.save(vehicleEngine);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
}
