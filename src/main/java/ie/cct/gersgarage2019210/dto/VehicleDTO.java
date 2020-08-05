package ie.cct.gersgarage2019210.dto;

import java.time.Year;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
	private Integer id;
	private Integer typeId;
	private String typeName;
	private Integer modelId;
	private String modelName;
	private Integer engineId;
	private String engineName;
	private String licence;
	private Year year;
	private Integer customerId;
}
