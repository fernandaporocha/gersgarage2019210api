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

import ie.cct.gersgarage2019210.dto.VehicleMakeDTO;
import ie.cct.gersgarage2019210.model.VehicleMake;
import ie.cct.gersgarage2019210.service.VehicleMakeService;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/vehicleMake")
public class VehicleMakeController {
	@Autowired
	private VehicleMakeService service;
	
	@PostMapping
	public void save(@RequestBody VehicleMakeDTO dto) {
		service.create(dto);
	}
	
	@GetMapping("/{id}")
	public VehicleMakeDTO find(@PathVariable Integer id) {
		VehicleMake vehicleMake = service.find(id);
		return vehicleMake==null?null:new VehicleMakeDTO(vehicleMake.getId(), 
				vehicleMake.getName(), 
				vehicleMake.getType().getId(), 
				vehicleMake.getType().getName());
	}
	
	@GetMapping("")
	public List<VehicleMakeDTO> getAll() {
		List<VehicleMake> list = service.findAll();
		List<VehicleMakeDTO> dtos = new ArrayList<VehicleMakeDTO>();
		list.forEach(vehicleMake -> dtos.add(new VehicleMakeDTO(vehicleMake.getId(), 
				vehicleMake.getName(), 
				vehicleMake.getType().getId(), 
				vehicleMake.getType().getName())));
		return dtos;
	}
	
	@GetMapping("/vehicleType/{typeId}")
	public List<VehicleMakeDTO> getMakesByTypeId(@PathVariable Integer typeId) {
		List<VehicleMake> list = service.findByTypeId(typeId);
		System.out.println(list);
		List<VehicleMakeDTO> dtos = new ArrayList<VehicleMakeDTO>();
		list.forEach(vehicleMake -> dtos.add(new VehicleMakeDTO(vehicleMake.getId(), 
				vehicleMake.getName(), 
				vehicleMake.getType().getId(), 
				vehicleMake.getType().getName())));
		return dtos;
	}
	
	@PutMapping
	public void update(@RequestBody VehicleMakeDTO dto) {
		System.out.println("update " +dto);
		service.update(dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

}

