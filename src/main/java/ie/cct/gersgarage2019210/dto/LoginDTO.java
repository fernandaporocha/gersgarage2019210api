package ie.cct.gersgarage2019210.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
	@NotBlank(message = "Can't be empty")
	private String username;
	@NotNull
	@Size(min=8)
	@NotBlank(message = "Can't be empty")
	private String password;
}
