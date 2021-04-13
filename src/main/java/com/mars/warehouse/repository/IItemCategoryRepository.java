package com.mars.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mars.warehouse.entity.ItemCategory;

@Repository
public interface IItemCategoryRepository extends JpaRepository<ItemCategory, Integer>{

	List<ItemCategory> findByEnable(boolean enable);
	List<ItemCategory> findTop5ByNameContaining(String name);
}
