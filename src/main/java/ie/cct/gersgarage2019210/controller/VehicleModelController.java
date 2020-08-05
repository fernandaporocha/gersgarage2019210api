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

import ie.cct.gersgarage2019210.dto.VehicleModelDTO;
import ie.cct.gersgarage2019210.model.VehicleModel;
import ie.cct.gersgarage2019210.service.VehicleModelService;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/vehicleModel")
public class VehicleModelController {
	@Autowired
	private VehicleModelService service;
	
	@PostMapping
	public void save(@RequestBody VehicleModelDTO dto) {
		service.create(dto);
	}
	
	@GetMapping("/{id}")
	public VehicleModelDTO find(@PathVariable Integer id) {
		VehicleModel vehicleModel = service.find(id);
		return vehicleModel==null?null:new VehicleModelDTO(
				vehicleModel.getId(), 
				vehicleModel.getName(), 
				vehicleModel.getMake().getId(), 
				vehicleModel.getMake().getName());
	}
	
	@GetMapping("")
	public List<VehicleModelDTO> getAll() {
		List<VehicleModel> list = service.findAll();
		List<VehicleModelDTO> dtos = new ArrayList<VehicleModelDTO>();
		list.forEach(vehicleModel -> dtos.add(new VehicleModelDTO(vehicleModel.getId(), vehicleModel.getName(), vehicleModel.getMake().getId(), vehicleModel.getMake().getName())));
		return dtos;
	}
	
	@GetMapping("/vehicleMake/{makeId}")
	public List<VehicleModelDTO> getModelsByMakeId(@PathVariable Integer makeId) {
		List<VehicleModel> list = service.findByMakeId(makeId);
		List<VehicleModelDTO> dtos = new ArrayList<VehicleModelDTO>();
		list.forEach(vehicleModel -> dtos.add(new VehicleModelDTO(vehicleModel.getId(), vehicleModel.getName(), vehicleModel.getMake().getId(), vehicleModel.getMake().getName())));
		return dtos;
	}
	
	@PutMapping
	public void update(@RequestBody VehicleModelDTO dto) {
		service.update(dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}

