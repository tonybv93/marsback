package com.mars.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mars.warehouse.entity.WarehouseType;

@Repository
public interface IWarehouseTypeRepository extends JpaRepository<WarehouseType, Integer>{

}
