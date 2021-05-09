package com.mars.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mars.warehouse.entity.Warehouse;

@Repository
public interface IWarehouseRepository extends JpaRepository<Warehouse, Integer>{
	List<Warehouse> findByEnable(boolean enable);
	List<Warehouse> findTop5ByNameContainingAndEnable(String name,boolean status);
}
