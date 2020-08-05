package ie.cct.gersgarage2019210.model;

import java.time.LocalDate;
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
	private LocalDate bookingDate;
	private Integer requiredSlots;
	private String comments;
	
	public Integer[] getServiceIds() {
		if(requiredServices.isEmpty()) {
			return null;
		}
		Integer[] ids = new Integer[requiredServices.size()];
		for (int i = 0; i < requiredServices.size();i++){
			ids[i] = requiredServices.get(i).getId();
		}
		return ids;
	}
}
