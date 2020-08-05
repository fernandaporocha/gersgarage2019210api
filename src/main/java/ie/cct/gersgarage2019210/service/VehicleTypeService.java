package ie.cct.gersgarage2019210.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gersgarage2019210.dto.VehicleTypeDTO;
import ie.cct.gersgarage2019210.model.VehicleType;
import ie.cct.gersgarage2019210.repository.VehicleTypeRepository;

@Service
public class VehicleTypeService {
	@Autowired
	private VehicleTypeRepository repository;
	
	public void create(VehicleTypeDTO dto) {
		VehicleType vehicleType = new VehicleType(null, dto.getName());
		repository.save(vehicleType);
	}
	
	public List<VehicleType> findAll(){
		return (List<VehicleType>) repository.findAll();
	}
	
	public VehicleType find(Integer id) {
		Optional<VehicleType> vehicleType = (Optional<VehicleType>) repository.findById(id);
		return vehicleType.get();
	}
	
	public void update(VehicleTypeDTO dto) {
		VehicleType vehicleType = find(dto.getId());
		vehicleType.setName(dto.getName());
		repository.save(vehicleType);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
}
