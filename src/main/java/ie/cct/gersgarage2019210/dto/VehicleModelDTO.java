package ie.cct.gersgarage2019210.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleModelDTO {
	private Integer id;
	private String name;
	private Integer makeId;
	private String makeName;
}
