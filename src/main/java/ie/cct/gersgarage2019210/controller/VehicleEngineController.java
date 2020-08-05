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

import ie.cct.gersgarage2019210.dto.VehicleEngineDTO;
import ie.cct.gersgarage2019210.model.VehicleEngine;
import ie.cct.gersgarage2019210.service.VehicleEngineService;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/vehicleEngine")
public class VehicleEngineController {
	@Autowired
	private VehicleEngineService service;
	
	@PostMapping
	public void save(@RequestBody VehicleEngineDTO dto) {
		service.create(dto);
	}
	
	@GetMapping("/{id}")
	public VehicleEngineDTO find(@PathVariable Integer id) {
		VehicleEngine vehicleEngine = service.find(id);
		return vehicleEngine==null?null:new VehicleEngineDTO(vehicleEngine.getId(), vehicleEngine.getName());
	}
	
	@GetMapping("")
	public List<VehicleEngineDTO> getAll() {
		List<VehicleEngine> list = service.findAll();
		List<VehicleEngineDTO> dtos = new ArrayList<VehicleEngineDTO>();
		list.forEach(vehicleEngine -> dtos.add(new VehicleEngineDTO(vehicleEngine.getId(), vehicleEngine.getName())));
		return dtos;
	}
	
	@PutMapping
	public void update(@RequestBody VehicleEngineDTO dto) {
		service.update(dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

}
