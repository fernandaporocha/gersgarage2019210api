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

import ie.cct.gersgarage2019210.dto.BookingTypeDTO;
import ie.cct.gersgarage2019210.model.BookingType;
import ie.cct.gersgarage2019210.service.BookingTypeService;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/bookingType")
public class BookingTypeController {
	@Autowired
	private BookingTypeService service;
	
	@PostMapping
	public void save(@RequestBody BookingTypeDTO dto) {
		service.create(dto);
	}
	
	@GetMapping("/{id}")
	public BookingTypeDTO find(@PathVariable Integer id) {
		BookingType bookingType = service.find(id);
		return bookingType==null?null:new BookingTypeDTO(
				bookingType.getId(), 
				bookingType.getName(), 
				bookingType.getPrice(), 
				bookingType.isDoubledSlot());
	}
	
	@GetMapping("")
	public List<BookingTypeDTO> getAll() {
		List<BookingType> list = service.findAll();
		List<BookingTypeDTO> dtos = new ArrayList<BookingTypeDTO>();
		list.forEach(bookingType -> dtos.add(new BookingTypeDTO(
				bookingType.getId(), 
				bookingType.getName(), 
				bookingType.getPrice(), 
				bookingType.isDoubledSlot())));
		return dtos;
	}
	
	@PutMapping
	public void update(@RequestBody BookingTypeDTO dto) {
		service.update(dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

}
