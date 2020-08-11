package ie.cct.gersgarage2019210.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchByDateDTO {
	LocalDate minDate;
	LocalDate maxDate;

}
