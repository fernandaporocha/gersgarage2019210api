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

import ie.cct.gersgarage2019210.dto.BookingStatusDTO;
import ie.cct.gersgarage2019210.model.BookingStatus;
import ie.cct.gersgarage2019210.service.BookingStatusService;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/bookingStatus")
public class BookingStatusController {
	
	@Autowired
	private BookingStatusService service;
	
	@PostMapping
	public void save(@RequestBody BookingStatusDTO dto) {
		service.create(dto);
	}
	
	@GetMapping("/{id}")
	public BookingStatusDTO find(@PathVariable Integer id) {
		BookingStatus bookingStatus = service.find(id);
		return bookingStatus==null?null:new BookingStatusDTO(bookingStatus.getId(), bookingStatus.getName());
	}
	
	@GetMapping("")
	public List<BookingStatusDTO> getAll() {
		List<BookingStatus> list = service.findAll();
		List<BookingStatusDTO> dtos = new ArrayList<BookingStatusDTO>();
		list.forEach(bookingStatus -> dtos.add(new BookingStatusDTO(bookingStatus.getId(), bookingStatus.getName())));
		return dtos;
	}
	
	@PutMapping
	public void update(@RequestBody BookingStatusDTO dto) {
		service.update(dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

}
