package ie.cct.gersgarage2019210.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.cct.gersgarage2019210.dto.ItemDTO;
import ie.cct.gersgarage2019210.model.Item;
import ie.cct.gersgarage2019210.service.ItemService;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/item")
public class ItemController {
	@Autowired
	private ItemService service;
	
	@PostMapping
	public void save(@RequestBody ItemDTO dto) {
		service.create(dto);
	}
	
	@GetMapping("/{id}")
	public ItemDTO find(@PathVariable Integer id) {
		Item item = service.find(id);
		return item==null?null:new ItemDTO(item.getId(), item.getName(), item.getQuantity(), item.getPrice());
	}
	
	@GetMapping("")
	public List<ItemDTO> getAll() {
		List<Item> list = service.findAll();
		List<ItemDTO> dtos = new ArrayList<ItemDTO>();
		list.forEach(item -> dtos.add(new ItemDTO(item.getId(), item.getName(), item.getQuantity(), item.getPrice())));
		return dtos;
	}
	
	@PutMapping
	public void update(@RequestBody ItemDTO dto) {
		service.update(dto);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}

}
