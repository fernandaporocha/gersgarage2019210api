package ie.cct.gersgarage2019210.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
	private Integer id;
	private Integer customerId;
	private Integer staffId;
	private Integer vehicleId;
	private Integer bookingTypeId;
	private Integer statusId;
	private Integer[] serviceIds;
	private LocalDate bookingDate;
	private String customerName;
	private String serviceName;
	private String staffName;
	private String status;
	private String comments;
	private List<BookingItemDTO> bookingItems;
	
}
