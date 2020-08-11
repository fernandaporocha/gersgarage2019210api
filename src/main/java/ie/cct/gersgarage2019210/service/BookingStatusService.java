package ie.cct.gersgarage2019210.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gersgarage2019210.dto.BookingStatusDTO;
import ie.cct.gersgarage2019210.model.BookingStatus;
import ie.cct.gersgarage2019210.repository.BookingStatusRepository;

@Service
public class BookingStatusService {
	@Autowired
	private BookingStatusRepository repository;
	
	public void create(BookingStatusDTO dto) {
		BookingStatus bookingStatus = new BookingStatus(null, dto.getName());
		repository.save(bookingStatus);
	}
	
	public List<BookingStatus> findAll(){
		return (List<BookingStatus>) repository.findAll();
	}
	
	public BookingStatus find(Integer id) {
		Optional<BookingStatus> bookingStatus = (Optional<BookingStatus>) repository.findById(id);
		return bookingStatus.get();
	}
	
	public BookingStatus findByName(String statusName) {
		Optional<BookingStatus> bookingStatus = (Optional<BookingStatus>) repository.findByName(statusName);
		return bookingStatus.get();
	}
	
	public void update(BookingStatusDTO dto) {
		BookingStatus bookingStatus = find(dto.getId());
		bookingStatus.setName(dto.getName());
		repository.save(bookingStatus);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
}
