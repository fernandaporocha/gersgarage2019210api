package ie.cct.gersgarage2019210.model;

import java.time.Year;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "type")
	private VehicleType type;
	@ManyToOne
	@JoinColumn(name = "model")
	private VehicleModel model;
	@ManyToOne
	@JoinColumn(name = "engine")
	private VehicleEngine engine;
	private String licence;
	private Year year;

}
