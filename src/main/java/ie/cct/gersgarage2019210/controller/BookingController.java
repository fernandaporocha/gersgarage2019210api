package ie.cct.gersgarage2019210.controller;

import java.io.FileNotFoundException;
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

import com.itextpdf.text.DocumentException;

import ie.cct.gersgarage2019210.dto.BookingDTO;
import ie.cct.gersgarage2019210.model.Booking;
import ie.cct.gersgarage2019210.service.BookingItemsService;
import ie.cct.gersgarage2019210.service.BookingService;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/booking")
public class BookingController {
	@Autowired
	private BookingService service;
	@Autowired
	private BookingItemsService bookingItemsService;

	@PostMapping
	public void save(@RequestBody BookingDTO dto) {
		service.create(dto);
	}
	
	@GetMapping("/{id}")
	public BookingDTO find(@PathVariable Integer id) {
		Booking booking = service.find(id);
		BookingDTO dto = booking==null?null:new BookingDTO(
				booking.getId(), 
				booking.getCustomer()==null?null:booking.getCustomer().getId(), 
				booking.getResponsibleStaff()==null?null:booking.getResponsibleStaff().getId(), 
				booking.getVehicle().getId(),
				booking.getRequiredBooking().getId(), 
				booking.getStatus()==null?null:booking.getStatus().getId(), 
				booking.getServiceIds(), 
				booking.getBookingDate(), 
				booking.getCustomer()==null?null:booking.getCustomer().getFirstName()+ " " + booking.getCustomer().getLastName(),
				booking.getRequiredBooking().getName(),
				booking.getResponsibleStaff()==null?null:booking.getResponsibleStaff().getFirstName() + " " + booking.getResponsibleStaff().getLastName(),
				booking.getStatus()==null?null:booking.getStatus().getName(),
				booking.getComments(),
				bookingItemsService.parseBookingItemDTO(service.getBookingItems(booking.getId())));
		return dto;
	}
	
	@GetMapping("")
	public List<BookingDTO> getAll() throws DocumentException, FileNotFoundException {
		List<Booking> list = service.findAll();
		List<BookingDTO> dtos = new ArrayList<BookingDTO>();
		list.forEach(booking -> dtos.add(new BookingDTO(booking.getId(), 
				booking.getCustomer()==null?null:booking.getCustomer().getId(), 
				booking.getResponsibleStaff()==null?null:booking.getResponsibleStaff().getId(), 
				booking.getVehicle().getId(), 
				booking.getRequiredBooking().getId(), 
				booking.getStatus()==null?null:booking.getStatus().getId(), 
				booking.getServiceIds(), 
				booking.getBookingDate(), 
				booking.getCustomer()==null?null:booking.getCustomer().getFirstName()+ " " + booking.getCustomer().getLastName(),
				booking.getRequiredBooking().getName(),
				booking.getResponsibleStaff()==null?null:booking.getResponsibleStaff().getFirstName() + " " + booking.getResponsibleStaff().getLastName(),
				booking.getStatus()==null?null:booking.getStatus().getName(),
				booking.getComments(),
				bookingItemsService.parseBookingItemDTO(service.getBookingItems(booking.getId())))));
		return dtos;
	}
	
	@PutMapping
	public void update(@RequestBody BookingDTO dto) {
		service.update(dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
		
	@PostMapping("/search")
	public List<BookingDTO> search(@RequestBody SearchByDateDTO dto) {
		List<Booking> list = service.findByDateRange(dto.getMinDate(), dto.getMaxDate());
		List<BookingDTO> dtos = new ArrayList<BookingDTO>();
		list.forEach(booking -> dtos.add(new BookingDTO(booking.getId(), 
				booking.getCustomer()==null?null:booking.getCustomer().getId(), 
				booking.getResponsibleStaff()==null?null:booking.getResponsibleStaff().getId(), 
				booking.getVehicle().getId(), 
				booking.getRequiredBooking().getId(), 
				booking.getStatus()==null?null:booking.getStatus().getId(), 
				booking.getServiceIds(), 
				booking.getBookingDate(),
				booking.getBookingDate()==null?null:service.localDateToString(booking.getBookingDate()),
				booking.getCustomer()==null?null:booking.getCustomer().getFirstName()+ " " + booking.getCustomer().getLastName(),
				booking.getRequiredBooking().getName(),
				booking.getResponsibleStaff()==null?null:booking.getResponsibleStaff().getFirstName() + " " + booking.getResponsibleStaff().getLastName(),
				booking.getStatus()==null?null:booking.getStatus().getName(),
				booking.getComments(),
				bookingItemsService.parseBookingItemDTO(service.getBookingItems(booking.getId())))));
		return dtos;
	}
}
