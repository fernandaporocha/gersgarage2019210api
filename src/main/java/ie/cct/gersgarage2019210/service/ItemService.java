package ie.cct.gersgarage2019210.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.cct.gersgarage2019210.dto.ItemDTO;
import ie.cct.gersgarage2019210.model.Item;
import ie.cct.gersgarage2019210.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
	private ItemRepository repository;
	
	public void create(ItemDTO dto) {
		Item item = new Item(null, dto.getName(),dto.getQuantity(), dto.getPrice());
		repository.save(item);
	}
	
	public List<Item> findAll(){
		return (List<Item>) repository.findAll();
	}
	
	public Item find(Integer id) {
		Optional<Item> item = (Optional<Item>) repository.findById(id);
		return item.get();
	}
	
	public List<Item> findAllById(List<Integer> ids){
		return (List<Item>) repository.findAllById(ids);
	}
	
	public void update(ItemDTO dto) {
		Item item = find(dto.getId());
		item.setName(dto.getName());
		item.setPrice(dto.getPrice());
		item.setQuantity(dto.getQuantity());
		repository.save(item);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
}
