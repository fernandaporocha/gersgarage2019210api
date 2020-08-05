package ie.cct.gersgarage2019210.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gersgarage2019210.dto.VehicleModelDTO;
import ie.cct.gersgarage2019210.model.VehicleModel;
import ie.cct.gersgarage2019210.repository.VehicleModelRepository;

@Service
public class VehicleModelService {
	@Autowired
	private VehicleModelRepository repository;
	@Autowired
	private VehicleMakeService vehicleMakeService;
	
	public void create(VehicleModelDTO dto) {
		VehicleModel vehicleModel = new VehicleModel(null, dto.getName(), vehicleMakeService.find(dto.getMakeId()));
		repository.save(vehicleModel);
	}
	
	public List<VehicleModel> findAll(){
		return (List<VehicleModel>) repository.findAll();
	}
	
	public List<VehicleModel> findByMakeId(Integer id){
		return (List<VehicleModel>) repository.findByMakeId(id);
	}
	
	public VehicleModel find(Integer id) {
		Optional<VehicleModel> vehicleModel = (Optional<VehicleModel>) repository.findById(id);
		return vehicleModel.get();
	}
	
	public void update(VehicleModelDTO dto) {
		VehicleModel vehicleModel = find(dto.getId());
		vehicleModel.setName(dto.getName());
		vehicleModel.setMake(vehicleMakeService.find(dto.getMakeId()));
		repository.save(vehicleModel);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}

}
