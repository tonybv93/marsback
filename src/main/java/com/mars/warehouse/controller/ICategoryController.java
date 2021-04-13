package com.mars.warehouse.controller;

import java.util.List;

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
import com.mars.shared.PickListDTO;
import com.mars.warehouse.dto.ItemCategoryDTO;
import com.mars.warehouse.service.ICategoryService;

@RestController
@RequestMapping("/wah")
public class ICategoryController {
	
	@Autowired ICategoryService categorys;
	
	// CATEGORY 
	
	@GetMapping("/categories/suggest")
	private List<PickListDTO> getCategorySuggestions(@RequestParam String text){
		return categorys.getCategorySuggestions(text);
	}
	
	@GetMapping("/categories")
	private List<ItemCategoryDTO> getAllCategories(){
		return categorys.getAllCategories();
	}
	
	@PostMapping("/category")
	private OperationResponseDTO saveCategory(@RequestBody ItemCategoryDTO dto, Authentication auth){
		Users u = (Users) auth.getPrincipal();
		return categorys.saveCategory(dto,u);
	}
	
	@DeleteMapping("/category")
	private OperationResponseDTO deleteCategory(@RequestParam int id){
		return categorys.deleteCategory(id);
	}
	
	// SUBCATEGORY
	@GetMapping("/subcategories/suggest")
	private List<PickListDTO> getSubcategorySuggestions(
			@RequestParam String text,
			@RequestParam(defaultValue = "0") int filter
			){
		return categorys.getSubcategorySuggestions(text, filter);
	}
	
	@PostMapping("/subcategory")
	private OperationResponseDTO saveSubcategory(@RequestBody ItemCategoryDTO dto, Authentication auth){
		Users u = (Users) auth.getPrincipal();
		return categorys.saveSubcategory(dto, u );
	}
	
	@DeleteMapping("/subcategory")
	private OperationResponseDTO deleteSubcategory(@RequestParam int id){
		return categorys.deleteSubcategory(id);
	}
}
