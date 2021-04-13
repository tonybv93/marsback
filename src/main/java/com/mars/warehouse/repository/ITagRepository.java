package com.mars.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mars.warehouse.entity.Tags;

@Repository
public interface ITagRepository extends JpaRepository<Tags, Integer>{

	@Query(nativeQuery = true, value = "select t.* from st_items_tags it left join st_tags t on it.st_tags = t.id where it.id_item = ?1")
	List<Tags> findByItem(int id_item);
	
}
