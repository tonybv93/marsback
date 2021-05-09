package com.mars.warehouse.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mars.auth.entity.Users;
import com.mars.shared.OperationResponseDTO;
import com.mars.shared.PageResponseDTO;
import com.mars.shared.PickListDTO;
import com.mars.warehouse.dto.ISpItemsList;
import com.mars.warehouse.dto.ItemDTO;
import com.mars.warehouse.service.IItemService;

@RestController
@RequestMapping("/wah")
public class ItemController {
	
	@Autowired IItemService items;
	
	
	@GetMapping("/item/suggest")
	public List<PickListDTO> getItemsSuggestions(@RequestParam String filter) {
		return items.getItemSuggestions(filter);
	}
	
	
	@GetMapping("/items")
	public PageResponseDTO<ISpItemsList> getAllItems(
			@RequestParam int page, 
			@RequestParam int size,
			@RequestParam(defaultValue = "ALL") String subcategory,
			@RequestParam(defaultValue = "ALL") String category,
			@RequestParam Optional<String> filter 
			) {
		return items.getItemList(filter.orElse(""), category, subcategory, page, size);
	}
	
	@GetMapping("/item")
	public ItemDTO getItem(@RequestParam int id) {
		return items.getItem(id);
	}
	
	@PostMapping("/item")
	public OperationResponseDTO saveItem(@RequestBody ItemDTO item, Authentication auth) {
		Users u = (Users) auth.getPrincipal();
		return items.saveItem(item, u);
	}
	
	@DeleteMapping("/item")
	public OperationResponseDTO deleteItem(@RequestParam int id) {
		return items.deleteItem(id);
	}
}
