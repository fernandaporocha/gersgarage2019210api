package ie.cct.gersgarage2019210.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.cct.gersgarage2019210.model.BookingItem;

@Repository
public interface BookingItemsRepository extends CrudRepository<BookingItem, Integer>{
	List<BookingItem> findByBookingId(Integer id);
}
