package ie.cct.gersgarage2019210.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "customer")
	private User customer;
	@ManyToOne
	@JoinColumn(name = "staff")
	private User responsibleStaff;
	@ManyToOne
	@JoinColumn(name = "vehicle")
	private Vehicle vehicle;
	private LocalDateTime entryDate;
	private LocalDateTime collectionDate;
	@ManyToOne
	@JoinColumn(name = "type")
	private BookingType requiredBooking;
	@ManyToOne
	@JoinColumn(name = "status")
	private BookingStatus status;
	@ManyToMany
	@JoinTable(
	  name = "booking_services", 
	  joinColumns = {@JoinColumn(name = "booking_id")}, 
	  inverseJoinColumns = {@JoinColumn(name = "service_id")}
	)
	List<ServiceType> requiredServices;
	@ManyToMany
	@JoinTable(
	  name = "booking_items", 
	  joinColumns = {@JoinColumn(name = "booking_id")}, 
	  inverseJoinColumns = {@JoinColumn(name = "item_id")}
	)
	List<Item> requiredItems;
	
	


}
