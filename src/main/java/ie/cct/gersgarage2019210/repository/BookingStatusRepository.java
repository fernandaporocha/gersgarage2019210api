package ie.cct.gersgarage2019210.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.cct.gersgarage2019210.model.BookingStatus;

@Repository
public interface BookingStatusRepository extends CrudRepository<BookingStatus, Integer>{

	Optional<BookingStatus> findByName(String statusName);
	
}
