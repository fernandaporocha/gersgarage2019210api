package ie.cct.gersgarage2019210.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ie.cct.gersgarage2019210.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

	List<User>findByIsStaffAndIsActive(boolean isStaff, boolean isActive);
	
	Optional<User>findByNameAndPassword(String name, String password);
	
	Optional<User>findByName(String name);
	
	Optional<User>findByEmail(String email);
	
	List<User>findByIsStaffAndIsAdminAndIsActive(boolean isStaff, boolean isAdmin, boolean isActive);

}
