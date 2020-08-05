package ie.cct.gersgarage2019210.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.cct.gersgarage2019210.model.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer>{
	
	List<Booking> findByCustomer(Integer customerId);
	
	List<Booking> findByBookingDate(LocalDate date);
}
