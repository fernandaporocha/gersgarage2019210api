package ie.cct.gersgarage2019210.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gersgarage2019210.dto.ContactDetailsDTO;
import ie.cct.gersgarage2019210.model.ContactDetails;
import ie.cct.gersgarage2019210.repository.ContactDetailsRepository;

@Service
public class ContactDetailsService {
	@Autowired
	private ContactDetailsRepository repository;
	
	public ContactDetails create(ContactDetailsDTO dto) {
		ContactDetails contactDetails = new ContactDetails(
				null, 
				dto.getAddress(),
				dto.getAddressExtraInformation(), 
				dto.getPhone(), 
				dto.getMobilePhone());
		ContactDetails savedContactDetails= repository.save(contactDetails);
		return savedContactDetails;
	}
	
	public List<ContactDetails> findAll(){
		return (List<ContactDetails>) repository.findAll();
	}
	
	public ContactDetails find(Integer id) {
		Optional<ContactDetails> contactDetails = (Optional<ContactDetails>) repository.findById(id);
		return contactDetails.get();
	}
	
	public void update(ContactDetailsDTO dto) {
		ContactDetails contactDetails = find(dto.getId());
		contactDetails.setAddress(dto.getAddress());
		contactDetails.setAddressExtraInformation(dto.getAddressExtraInformation());
		contactDetails.setPhone(dto.getPhone());
		contactDetails.setMobilePhone(dto.getMobilePhone());
		repository.save(contactDetails);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}

}
