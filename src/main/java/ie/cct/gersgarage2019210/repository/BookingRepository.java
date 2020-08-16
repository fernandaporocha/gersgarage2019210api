package ie.cct.gersgarage2019210.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.cct.gersgarage2019210.model.Booking;
import ie.cct.gersgarage2019210.model.User;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer>{
	
	List<Booking> findByCustomer(User customerId);
	
	List<Booking> findByResponsibleStaff(User mechanic);
	
	List<Booking> findByDate(LocalDate date);
	
	//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	List<Booking> findByDateBetween (LocalDate minDate, LocalDate maxDate);
	
	List<Booking> findByResponsibleStaffAndDate(User mechanic, LocalDate date);
	
}
