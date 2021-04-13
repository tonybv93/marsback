package com.mars.warehouse.service;

import java.util.List;

import com.mars.auth.entity.Users;
import com.mars.shared.OperationResponseDTO;
import com.mars.shared.PickListDTO;
import com.mars.warehouse.dto.ItemCategoryDTO;

public interface ICategoryService {
	// CATEGORY
	public List<PickListDTO> getCategorySuggestions(String text);
	public List<ItemCategoryDTO> getAllCategories();
	public OperationResponseDTO saveCategory(ItemCategoryDTO dto,Users u);
	public OperationResponseDTO deleteCategory(int id);
	
	// SUB CATEGORY
	public List<PickListDTO> getSubcategorySuggestions(String text, int cat);
	public OperationResponseDTO saveSubcategory(ItemCategoryDTO dto, Users u);
	public OperationResponseDTO deleteSubcategory(int id);
}
