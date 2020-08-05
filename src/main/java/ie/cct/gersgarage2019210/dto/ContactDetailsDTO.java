package ie.cct.gersgarage2019210.dto;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@CrossOrigin("*")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetailsDTO {
	private Integer id;
	private String address;
	private String addressExtraInformation;
	private String phone;
	private String mobilePhone;
}
