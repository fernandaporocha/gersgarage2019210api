package ie.cct.gersgarage2019210.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.cct.gersgarage2019210.dto.ContactDetailsDTO;
import ie.cct.gersgarage2019210.model.ContactDetails;
import ie.cct.gersgarage2019210.service.ContactDetailsService;

@RestController
@RequestMapping(path="/contactDetails")
public class ContactDetailsController {
	@Autowired
	private ContactDetailsService service;
	
	@PostMapping
	public void save(@RequestBody ContactDetailsDTO dto) {
		service.create(dto);
	}
	
	@GetMapping("/{id}")
	public ContactDetailsDTO find(@PathVariable Integer id) {
		ContactDetails contactDetails = service.find(id);
		return contactDetails==null?null:new ContactDetailsDTO(
				contactDetails.getId(), 
				contactDetails.getAddress(), 
				contactDetails.getAddressExtraInformation(), 
				contactDetails.getPhone(), 
				contactDetails.getMobilePhone());
	}
	
	@GetMapping("")
	public List<ContactDetailsDTO> getAll() {
		List<ContactDetails> list = service.findAll();
		List<ContactDetailsDTO> dtos = new ArrayList<ContactDetailsDTO>();
		list.forEach(contactDetails -> dtos.add(new ContactDetailsDTO(
				contactDetails.getId(), 
				contactDetails.getAddress(), 
				contactDetails.getAddressExtraInformation(), 
				contactDetails.getPhone(), 
				contactDetails.getMobilePhone())));
		return dtos;
	}
	
	@PutMapping
	public void update(@RequestBody ContactDetailsDTO dto) {
		service.update(dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

}
