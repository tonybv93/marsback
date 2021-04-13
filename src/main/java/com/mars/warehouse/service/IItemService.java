package com.mars.warehouse.service;

import java.util.List;

import com.mars.auth.entity.Users;
import com.mars.shared.OperationResponseDTO;
import com.mars.shared.PageResponseDTO;
import com.mars.shared.PickListDTO;
import com.mars.warehouse.dto.ISpItemsList;
import com.mars.warehouse.dto.ItemDTO;

public interface IItemService {
	public List<PickListDTO> getItemSuggestions(String text);
	public ItemDTO getItem(int id);
	public PageResponseDTO<ISpItemsList> getItemList(String filter, String category, String subcategory, int page, int size);
	public OperationResponseDTO saveItem(ItemDTO dto, Users u);
	public OperationResponseDTO deleteItem(int id);
	
	// TAGs
	
}
