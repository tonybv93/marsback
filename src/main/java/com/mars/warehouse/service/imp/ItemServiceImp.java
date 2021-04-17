package com.mars.warehouse.service.imp;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mars.auth.entity.Users;
import com.mars.shared.OperationResponseDTO;
import com.mars.shared.PageResponseDTO;
import com.mars.shared.PickListDTO;
import com.mars.warehouse.dto.CategoryDetailsDTO;
import com.mars.warehouse.dto.ISpItemsList;
import com.mars.warehouse.dto.ItemDTO;
import com.mars.warehouse.dto.ItemTagDTO;
import com.mars.warehouse.entity.Item;
import com.mars.warehouse.repository.IItemRepository;
import com.mars.warehouse.repository.IItemSubCategoryRepository;
import com.mars.warehouse.repository.ITagRepository;
import com.mars.warehouse.service.IItemService;

@Service
public class ItemServiceImp implements IItemService {

	@Autowired IItemRepository itemrepo;
	@Autowired ITagRepository tagrepo;
	@Autowired IItemSubCategoryRepository subcatrepo;
	
	@Override
	public PageResponseDTO<ISpItemsList> getItemList(String filter, String category, String subcategory, int page, int size) {
		
		return new PageResponseDTO<>(page, size, 0, itemrepo.getItemList("%" + filter + "%", category, subcategory, page*size, size));
		
	}

	@Override
	public List<PickListDTO> getItemSuggestions(String text) {
		return itemrepo.findTop5ByNameContaining(text).stream()
				.map(p-> new PickListDTO(null, p.getId(), p.getName() + " " + p.getDescription(), p.getModel() + " - " + p.getBrand()))
				.collect(Collectors.toList());
	}

	@Override
	public ItemDTO getItem(int id) {		
		Item item = itemrepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		List<ItemTagDTO> tags = tagrepo.findByItem(id).stream()
				.map(p-> new ItemTagDTO(p.getId(), p.getName(), p.getDescription(), p.getColor(), p.getCategory()))
				.collect(Collectors.toList());
		
		return new ItemDTO(
				item.getId(), 
				item.getName(), 
				item.getDescription(),
				item.getSkuCode(),
				item.getModel(),
				item.getBrand(),
				item.getDistributor(),
				item.getColor(),
				item.getPackaging(),
				item.getSpecifications(),
				item.getCalification(),
				item.getItemVersion(),
				item.getPhotoUri(),
				subcatrepo.findByItem(id).map(p -> new CategoryDetailsDTO(p.getCatid(), p.getCatname(), p.getSubid(), p.getSubname()) ).orElse(null),
				tags);
	}

	@Override
	public OperationResponseDTO saveItem(ItemDTO dto, Users u) {
		Item item = null;
		
		try {
			if (dto.getId()==null) {
				item = new Item(
						null, 
						dto.getName(), 
						dto.getDescription(),
						dto.getSkuCode(),
						dto.getModel(),
						dto.getBrand(),
						dto.getDistributor(),
						dto.getColor(), 
						dto.getPhotoUri(),
						dto.getVersion(),
						dto.getPackaging(),
						dto.getCalification(),
						dto.getSpecifications(),
						subcatrepo.getOne(dto.getCategory().getSubid()) );
				
				item.setCreatedBy(u.getUsername());	
			}else {
				item = itemrepo.findById(dto.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
				item.setName(dto.getName());
				item.setDescription(dto.getDescription());
				item.setSkuCode(dto.getSkuCode());
				item.setModel(dto.getModel());
				item.setBrand(dto.getBrand());
				item.setDistributor(dto.getDistributor());
				item.setColor(dto.getColor());
				item.setPhotoUri(dto.getPhotoUri());
				item.setItemVersion(dto.getVersion());
				item.setPackaging(dto.getPackaging());
				item.setCalification(dto.getCalification());
				item.setSpecifications(dto.getSpecifications());
				item.setSubCategory( subcatrepo.getOne(dto.getCategory().getSubid()) );
				
				item.setUpdatedBy(u.getUsername());
				item.setUpdatedAt(new Date());
			}
			item = itemrepo.save(item);
			
			return new OperationResponseDTO(item.getId(), null, true, null);
		} catch (Exception e) {
			return new OperationResponseDTO(null, null, false, e.getMessage());
		}
	}

	@Override
	public OperationResponseDTO deleteItem(int id) {
		try {
			itemrepo.deleteById(id);
			return new OperationResponseDTO(id, null, true, null);
		} catch (Exception e) {
			return new OperationResponseDTO(null, null, false, e.getMessage());
		}
		
	}
	
	// TAGS

}
