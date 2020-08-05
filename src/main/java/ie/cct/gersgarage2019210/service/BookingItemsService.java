package ie.cct.gersgarage2019210.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gersgarage2019210.dto.BookingItemDTO;
import ie.cct.gersgarage2019210.model.Booking;
import ie.cct.gersgarage2019210.model.BookingItem;
import ie.cct.gersgarage2019210.repository.BookingItemsRepository;

@Service
public class BookingItemsService {
	@Autowired
	private BookingItemsRepository repository;
	@Autowired
	private ItemService itemService;
	
	public List<BookingItem> getItemsByBookingId(Integer id){
		return repository.findByBookingId(id);
	}
	
	public List<BookingItemDTO> parseBookingItemDTO(List<BookingItem>bookingItems){
		List<BookingItemDTO> dtos = new ArrayList<BookingItemDTO>();
		for (BookingItem item: bookingItems) {
			dtos.add(new BookingItemDTO(item.getBooking().getId(),item.getItem().getId(),item.getItem().getName(), item.getQuantity()));
		}
		return dtos;
	}
	
	public void save(List<BookingItemDTO> dtos, Booking booking) {
		for(BookingItemDTO dto: dtos) {
			dto.setBookingId(booking.getId());
			BookingItem item = new BookingItem(null, booking,itemService.find(dto.getItemId()), dto.getQuantity());
			repository.save(item);
		}
	}
	
	public void deleteByBookingId(Integer bookingId) {
		List<BookingItem> itemsByBookingId = getItemsByBookingId(bookingId);
		repository.deleteAll(itemsByBookingId);
	}
}
