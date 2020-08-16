package ie.cct.gersgarage2019210.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gersgarage2019210.dto.ServiceTypeDTO;
import ie.cct.gersgarage2019210.model.ServiceType;
import ie.cct.gersgarage2019210.repository.ServiceTypeRepository;

@Service
public class ServiceTypeService {
	@Autowired
	private ServiceTypeRepository repository;
	
	public void create(ServiceTypeDTO dto) {
		ServiceType serviceType = new ServiceType(null, dto.getName(), dto.getPrice());
		repository.save(serviceType);
	}
	
	public List<ServiceType> findAll(){
		return (List<ServiceType>) repository.findAll();
	}
	
	public ServiceType find(Integer id) {
		Optional<ServiceType> serviceType = (Optional<ServiceType>) repository.findById(id);
		return serviceType.get();
	}
	
	public List<ServiceType> findAllById(List<Integer> ids){
		return (List<ServiceType>) repository.findAllById(ids);
	}
	
	public void update(ServiceTypeDTO dto) {
		ServiceType serviceType = find(dto.getId());
		serviceType.setName(dto.getName());
		serviceType.setPrice(dto.getPrice());
		repository.save(serviceType);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
}
