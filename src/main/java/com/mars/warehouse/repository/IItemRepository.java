package com.mars.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mars.warehouse.dto.ISpItemsList;
import com.mars.warehouse.entity.Item;

@Repository
public interface IItemRepository extends JpaRepository<Item, Integer>{
	
	@Query(nativeQuery = true, value = "call sp_items_list(?1, ?2, ?3,?4,?5)")
	List<ISpItemsList> getItemList(String filter,String subcategory,String category,int offset, int size);
	List<Item> findTop5ByNameContaining(String name);
}
