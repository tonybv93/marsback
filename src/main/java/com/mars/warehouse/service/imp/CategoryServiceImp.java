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
import com.mars.shared.PickListDTO;
import com.mars.warehouse.dto.ItemCategoryDTO;
import com.mars.warehouse.entity.ItemCategory;
import com.mars.warehouse.entity.ItemSubcategory;
import com.mars.warehouse.repository.IItemCategoryRepository;
import com.mars.warehouse.repository.IItemSubCategoryRepository;
import com.mars.warehouse.service.ICategoryService;

@Service
public class CategoryServiceImp implements ICategoryService {

	@Autowired IItemCategoryRepository categoryr;

	// Devuelve un arreglo con tadas las categorías + las subcategorías
	@Override
	public List<ItemCategoryDTO> getAllCategories() {
		return categoryr.findByEnable(true).stream()
				.map(p-> new ItemCategoryDTO(
						p.getId(), 
						p.getName(), 
						p.getDescription(), 
						p.getImguri(), 
						null,
						p.getSubcategories().stream().map(sc-> 
							new ItemCategoryDTO(sc.getId(), sc.getName(), sc.getDescription(), sc.getImguri(), p.getId(), null))
							.collect(Collectors.toList()) ) )
				.collect(Collectors.toList());
	}
	
	// Devuelve una lista de sugerencias al buscar categorías
	@Override
	public List<PickListDTO> getCategorySuggestions(String text) {
		return categoryr.findTop5ByNameContaining(text).stream()
				.map(c-> new PickListDTO(null, c.getId(), c.getName(), null))
				.collect(Collectors.toList());
	}

	// Guardar o Editar categoría
	@Override
	public OperationResponseDTO saveCategory(ItemCategoryDTO dto, Users u) {
		
		ItemCategory category = null;
		
		if (dto.getId()==null) {
		// CREAR
			category = new ItemCategory(null, dto.getName(), dto.getDescription(), dto.getImg(), null);
			category.setCreatedBy(u.getUsername());
			category.setCreatedAt(new Date());
		}else {
		// ACTUALIZAR
			category = categoryr.findById(dto.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
			category.setName(dto.getName());
			category.setDescription(dto.getDescription());
			category.setImguri(dto.getImg());
			
			category.setUpdatedBy(u.getUsername());
		}
		
		try {
			category = categoryr.save(category);
			return new OperationResponseDTO(category.getId(), null, true,null);
		} catch (Exception e) {
			return new OperationResponseDTO(null, null, false, e.getMessage());
		}
	}

	// Borrar categoría
	@Override
	public OperationResponseDTO deleteCategory(int id) {		
		ItemCategory category = categoryr.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
		if (category.getSubcategories().size()>0) {
			return new OperationResponseDTO(null, null, false, "Primero debe eliminar las subcategorías.");
		}		
		try {
			categoryr.deleteById(id);
			return new OperationResponseDTO(id, null, true,null);
		} catch (Exception e) {
			return new OperationResponseDTO(null, null, false, e.getMessage());
		}
	}

	
	// SUBCATEGORÍAS
	
	@Autowired IItemSubCategoryRepository screpo;
		
	@Override
	public List<PickListDTO> getSubcategorySuggestions(String text, int filter) {
		return screpo.findSuggestions(text,filter).stream()
				.map(p-> new PickListDTO(null, p.getId(), p.getName(), p.getDescription()))
				.collect(Collectors.toList());
	}

	@Override
	public OperationResponseDTO saveSubcategory(ItemCategoryDTO dto, Users u) {
		
		ItemSubcategory isc = null;
		
		if (dto.getId() ==null) {
			isc = new ItemSubcategory(dto.getId(), dto.getName(), dto.getDescription(), dto.getImg(), 
					categoryr.getOne(dto.getParentid()) );
			
			isc.setCreatedBy(u.getUsername());
			isc.setCreatedAt(new Date());
		}else {
			isc = screpo.findById(dto.getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
			isc.setName(dto.getName());
			isc.setDescription(dto.getDescription());
			isc.setImguri(dto.getImg());
			
			isc.setUpdatedBy(u.getUsername());
			isc.setUpdatedAt( new Date() );
		}
		
		try {
			screpo.save(isc);
			return new OperationResponseDTO(isc.getId(), null, true, null);
		} catch (Exception e) {
			return new OperationResponseDTO(null, null, false, e.getMessage());
		}
	}

	@Override
	public OperationResponseDTO deleteSubcategory(int id) {
		if  (screpo.countItemsOnSubcateogry(id) > 0 ) {
			return new OperationResponseDTO(null, null, false, "Esta subcategoría tiene Items asociados.");
		}
		try {
			screpo.deleteById(id);
			return new OperationResponseDTO(id, null, true,null);
		} catch (Exception e) {
			return new OperationResponseDTO(null, null, false, e.getMessage());
		}
	}
	
	
}
