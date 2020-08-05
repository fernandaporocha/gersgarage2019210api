package ie.cct.gersgarage2019210.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gersgarage2019210.dto.BookingTypeDTO;
import ie.cct.gersgarage2019210.model.BookingType;
import ie.cct.gersgarage2019210.repository.BookingTypeRepository;

@Service
public class BookingTypeService {
	@Autowired
	private BookingTypeRepository repository;
	
	public void create(BookingTypeDTO dto) {
		BookingType bookingType = new BookingType(null, dto.getName(), dto.getPrice(), dto.isDoubledSlot());
		repository.save(bookingType);
	}
	
	public List<BookingType> findAll(){
		return (List<BookingType>) repository.findAll();
	}
	
	public BookingType find(Integer id) {	
		Optional<BookingType> bookingType = repository.findById(id);
		if (bookingType.isPresent()) {
			return bookingType.get();
		}
		else {
			return null;
		}
	}
	
	public void update(BookingTypeDTO dto) {
		BookingType bookingType = find(dto.getId());
		bookingType.setName(dto.getName());
		bookingType.setPrice(dto.getPrice());
		bookingType.setDoubledSlot(dto.isDoubledSlot());
		repository.save(bookingType);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
}
