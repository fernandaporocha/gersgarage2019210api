package ie.cct.gersgarage2019210.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "user_role")
	private Role role;
	private String firstName;
	private String lastName;
	private String email;
	@ManyToOne
	@JoinColumn(name = "contact")
	private ContactDetails contactDetails;
	private boolean isStaff;
	private boolean isAdmin;
	private boolean isActive;
	private String password;


	

}
