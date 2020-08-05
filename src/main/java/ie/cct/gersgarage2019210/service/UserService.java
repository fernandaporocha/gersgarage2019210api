package ie.cct.gersgarage2019210.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gersgarage2019210.dto.ContactDetailsDTO;
import ie.cct.gersgarage2019210.dto.UserDTO;
import ie.cct.gersgarage2019210.model.ContactDetails;
import ie.cct.gersgarage2019210.model.User;
import ie.cct.gersgarage2019210.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private ContactDetailsService contactDetailsService;
	
	public void create(UserDTO dto) {
		User user = new User(
				null, 
				dto.getName(), 
				dto.getFirstName(), 
				dto.getLastName(), 
				dto.getEmail(), 
				contactDetailsService.find(dto.getContactDetailsId()), 
				dto.isStaff(), 
				dto.isAdmin(), 
				dto.isActive(),
				dto.getPassword());
		repository.save(user);
	}
	
	public void create(UserDTO dto, ContactDetailsDTO contactDTO) {
		ContactDetails contactDetails = contactDetailsService.create(contactDTO);
		User user = new User(
				null, 
				dto.getName(), 
				dto.getFirstName(),
				dto.getLastName(), 
				dto.getEmail(), 
				contactDetails, 
				dto.isStaff(), 
				dto.isAdmin(), 
				dto.isActive(),
				dto.getPassword());
		repository.save(user);
	}
	
	public List<User> findAll(){
		return (List<User>) repository.findAll();
	}
	
	public User find(Integer id) {
		Optional<User> user = (Optional<User>) repository.findById(id);
		return user.get();
	}
	
	public void update(UserDTO dto) {
		contactDetailsService.update(dto.getContactDetails());
		User user = find(dto.getId());
		user.setName(dto.getName());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setActive(dto.isActive());
		user.setStaff(dto.isStaff());
		user.setAdmin(dto.isAdmin());
		user.setPassword(dto.getPassword());
		
		repository.save(user);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public List<User> getAllCustomer(){
		return repository.findByIsStaffAndIsAdminAndIsActive(false, false, true);
	}
	

	public List<User> findByIsStaffAndIsActive(boolean isStaff, boolean isActive){
		return (List<User>)repository.findByIsStaffAndIsActive(isStaff, isActive); 
	}
	
	public User login(String username, String password) {
		Optional<User> user = (Optional<User>) repository.findByNameAndPassword(username, password);
		return user.isPresent()?user.get():null;
	}
}
