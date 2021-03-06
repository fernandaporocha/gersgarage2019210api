package ie.cct.gersgarage2019210.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingItemDTO {
	Integer bookingId;
	Integer itemId;
	String itemName;
	Integer quantity;
}
