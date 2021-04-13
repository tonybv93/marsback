package com.mars.warehouse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mars.warehouse.dto.SPCategoryDetailsDTO;
import com.mars.warehouse.entity.ItemSubcategory;

@Repository
public interface IItemSubCategoryRepository extends JpaRepository<ItemSubcategory, Integer>{
	
	@Query(nativeQuery = true, value = "select count(id) from st_item where sub_category = ?1")
	int countItemsOnSubcateogry(int idsubcategory);
	
	@Query(nativeQuery = true,value = "call sp_subcategory_suggest(?1,?2)")
	List<ItemSubcategory> findSuggestions(String  filter, int idcategory);

	@Query(nativeQuery = true, value = "call sp_item_category(?1)")
	Optional<SPCategoryDetailsDTO> findByItem(int id_item);
	
}
