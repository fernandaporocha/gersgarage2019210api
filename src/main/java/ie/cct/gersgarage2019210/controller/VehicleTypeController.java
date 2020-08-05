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

import ie.cct.gersgarage2019210.dto.VehicleTypeDTO;
import ie.cct.gersgarage2019210.model.VehicleType;
import ie.cct.gersgarage2019210.service.VehicleTypeService;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/vehicleType")
public class VehicleTypeController {
	@Autowired
	private VehicleTypeService service;
	
	@PostMapping
	public void save(@RequestBody VehicleTypeDTO dto) {
		service.create(dto);
	}
	
	@GetMapping("/{id}")
	public VehicleTypeDTO find(@PathVariable Integer id) {
		VehicleType vehicleType = service.find(id);
		return vehicleType==null?null:new VehicleTypeDTO(vehicleType.getId(), vehicleType.getName());
	}
	
	@GetMapping("")
	public List<VehicleTypeDTO> getAll() {
		List<VehicleType> list = service.findAll();
		List<VehicleTypeDTO> dtos = new ArrayList<VehicleTypeDTO>();
		list.forEach(vehicleType -> dtos.add(new VehicleTypeDTO(vehicleType.getId(), vehicleType.getName())));
		return dtos;
	}
	
	@PutMapping
	public void update(@RequestBody VehicleTypeDTO dto) {
		service.update(dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

}
