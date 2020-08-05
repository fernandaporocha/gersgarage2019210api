package ie.cct.gersgarage2019210.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingTypeDTO {
	private Integer id;
	private String name;
	private BigDecimal price;
	private boolean doubledSlot;
}
