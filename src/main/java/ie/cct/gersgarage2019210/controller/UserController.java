package ie.cct.gersgarage2019210.controller;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ie.cct.gersgarage2019210.dto.ContactDetailsDTO;
import ie.cct.gersgarage2019210.dto.LoginDTO;
import ie.cct.gersgarage2019210.dto.UserDTO;
import ie.cct.gersgarage2019210.exceptions.InvalidRequestException;
import ie.cct.gersgarage2019210.model.User;
import ie.cct.gersgarage2019210.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/user")
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping
	public void save(@RequestBody UserDTO dto) {
		service.create(dto, dto.getContactDetails());
	}
	
	@GetMapping("/{id}")
	public UserDTO find(@PathVariable Integer id) {
		User user = service.find(id);
		return user==null?null:new UserDTO(
				user.getId(), 
				user.getName(), 
				user.getFirstName(), 
				user.getLastName(), 
				user.getEmail(), 
				user.getContactDetails().getId(), 
				new ContactDetailsDTO( user.getContactDetails().getId(),
					user.getContactDetails().getAddress(),
					user.getContactDetails().getAddressExtraInformation(),
					user.getContactDetails().getPhone(),
					user.getContactDetails().getMobilePhone()), 
				user.isStaff(), 
				user.isAdmin(), 
				user.isActive(),
				user.getPassword());
	}
	
	@GetMapping("")
	public List<UserDTO> getAll() {
		List<User> list = service.findAll();
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		list.forEach(user -> dtos.add(new UserDTO(
				user.getId(), 
				user.getName(), 
				user.getFirstName(), 
				user.getLastName(), 
				user.getEmail(), 
				user.getContactDetails()==null?null:user.getContactDetails().getId(),
				user.getContactDetails()==null?null:
					new ContactDetailsDTO( user.getContactDetails().getId(),
						user.getContactDetails().getAddress(),
						user.getContactDetails().getAddressExtraInformation(),
						user.getContactDetails().getPhone(),
						user.getContactDetails().getMobilePhone()),
				user.isStaff(), 
				user.isAdmin(), 
				user.isActive(),
				user.getPassword())));
		return dtos;
	}
	
	@GetMapping("getStaff")
	public List<UserDTO> getStaff() {
		List<User> list = service.findByIsStaffAndIsActive(true, true);
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		list.forEach(user -> dtos.add(
				new UserDTO(
						user.getId(), 
						user.getName(), 
						user.getFirstName(), 
						user.getLastName(), 
						user.getEmail(), 
						user.getContactDetails()==null?null:user.getContactDetails().getId(),
						user.getContactDetails()==null?null:
							new ContactDetailsDTO(user.getContactDetails().getId(),
									user.getContactDetails().getAddress(),
									user.getContactDetails().getAddressExtraInformation(),
									user.getContactDetails().getPhone(),
									user.getContactDetails().getMobilePhone()), 
						user.isStaff(), 
						user.isAdmin(), 
						user.isActive(),
						user.getPassword())));
		return dtos;
	}
	
	@PutMapping
	public void update(@RequestBody UserDTO dto) {
		service.update(dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@PostMapping("login")
	public UserDTO login(@RequestBody LoginDTO dto,  BindingResult bindingResult) {
		checkInput(dto, bindingResult);
		User user = service.login(dto.getUsername(), dto.getPassword());
		UserDTO userDTO = user==null?null:new UserDTO(
				user.getId(), 
				user.getName(), 
				user.getFirstName(), 
				user.getLastName(), 
				user.getEmail(), 
				user.getContactDetails()==null?null:
					user.getContactDetails().getId(),
				user.getContactDetails()==null?null:
					new ContactDetailsDTO(user.getContactDetails().getId(),
							user.getContactDetails().getAddress(),
							user.getContactDetails().getAddressExtraInformation(),
							user.getContactDetails().getPhone(),
							user.getContactDetails().getMobilePhone()), 
				user.isStaff(), 
				user.isAdmin(), 
				user.isActive(),
				user.getPassword());
		return userDTO;
	}
	
	private void checkInput(@RequestBody LoginDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(bindingResult);
        }
        if (service.findByUsername(dto.getUsername()).isPresent()) {
        	System.out.println("checkinput");
            bindingResult.rejectValue("username", "DUPLICATED", "Duplicated username");
            bindingResult.rejectValue("password", "DUPLICATED", "Duplicated password");
        }

/*        if (service.findByEmail(dto.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "DUPLICATED", "Duplicated email");
    }*/    

        if (bindingResult.hasErrors()) {
        	System.out.println(bindingResult);
            throw new InvalidRequestException(bindingResult);
        }
    }
}
