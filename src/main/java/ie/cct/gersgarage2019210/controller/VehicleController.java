package ie.cct.gersgarage2019210.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.cct.gersgarage2019210.dto.VehicleDTO;
import ie.cct.gersgarage2019210.model.Vehicle;
import ie.cct.gersgarage2019210.service.VehicleService;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/vehicle")
public class VehicleController {
	@Autowired
	private VehicleService service;
	
	@PostMapping
	public void save(@RequestBody VehicleDTO dto) {
		service.create(dto);
	}
		
	@GetMapping("/{id}")
	public VehicleDTO find(@PathVariable Integer id) {
		Vehicle vehicle = service.find(id);
		if (vehicle==null) {
			return null;
		}else {
		return vehicle==null?null:new VehicleDTO(
				vehicle.getId(),
				vehicle.getModel().getId(),
				vehicle.getModel().getName(),
				vehicle.getModel()==null?null:vehicle.getModel().getMake().getId(),
				vehicle.getModel()==null?null:vehicle.getModel().getMake().getName(),
				vehicle.getModel()==null||vehicle.getModel().getMake()==null?null:vehicle.getModel().getMake().getType().getId(),		
				vehicle.getModel()==null||vehicle.getModel().getMake()==null?null:vehicle.getModel().getMake().getType().getName(),
				vehicle.getEngine().getId(),
				vehicle.getEngine().getName(),
				vehicle.getLicence(), 
				vehicle.getYear(),
				vehicle.getCustomer()==null?null:vehicle.getCustomer().getId());
		}
	}
	
	@GetMapping("")
	public List<VehicleDTO> getAll() {
		List<Vehicle> list = service.findAll();
		List<VehicleDTO> dtos = new ArrayList<VehicleDTO>();
		list.forEach(vehicle -> dtos.add(new VehicleDTO(
				vehicle.getId(), 
				vehicle.getModel().getId(),
				vehicle.getModel().getName(),
				vehicle.getModel()==null?null:vehicle.getModel().getMake().getId(),
				vehicle.getModel()==null?null:vehicle.getModel().getMake().getName(),
				vehicle.getModel()==null||vehicle.getModel().getMake()==null?null:vehicle.getModel().getMake().getType().getId(),		
				vehicle.getModel()==null||vehicle.getModel().getMake()==null?null:vehicle.getModel().getMake().getType().getName(),
				vehicle.getEngine().getId(),
				vehicle.getEngine().getName(), 
				vehicle.getLicence(), vehicle.getYear(),
				vehicle.getCustomer()==null?null:vehicle.getCustomer().getId())));
		return dtos;
	}
	
	@PutMapping
	public void update(@RequestBody VehicleDTO dto) {
		service.update(dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

}
