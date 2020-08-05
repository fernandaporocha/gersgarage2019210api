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

import ie.cct.gersgarage2019210.dto.ServiceTypeDTO;
import ie.cct.gersgarage2019210.model.ServiceType;
import ie.cct.gersgarage2019210.service.ServiceTypeService;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/serviceType")
public class ServiceTypeController {
	@Autowired
	private ServiceTypeService service;
	
	@PostMapping
	public void save(@RequestBody ServiceTypeDTO dto) {
		service.create(dto);
	}
	
	@GetMapping("/{id}")
	public ServiceTypeDTO find(@PathVariable Integer id) {
		ServiceType serviceType = service.find(id);
		return serviceType==null?null:new ServiceTypeDTO(serviceType.getId(), serviceType.getName(), serviceType.getPrice());
	}
	
	@GetMapping("")
	public List<ServiceTypeDTO> getAll() {
		List<ServiceType> list = service.findAll();
		List<ServiceTypeDTO> dtos = new ArrayList<ServiceTypeDTO>();
		list.forEach(serviceType -> dtos.add(new ServiceTypeDTO(serviceType.getId(), serviceType.getName(), serviceType.getPrice())));
		return dtos;
	}
	
	@PutMapping
	public void update(@RequestBody ServiceTypeDTO dto) {
		service.update(dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

}

