package ie.cct.gersgarage2019210.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private Integer id;
	private String name;
	private String firstName;
	private String lastName;
	private String email;
	private Integer contactDetailsId;
	private ContactDetailsDTO contactDetails;
	private boolean staff;
	private boolean admin;
	private boolean active;
	private String password;
}
