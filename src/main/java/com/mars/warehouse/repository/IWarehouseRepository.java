package com.mars.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mars.warehouse.entity.Warehouse;

@Repository
public interface IWarehouseRepository extends JpaRepository<Warehouse, Integer>{

}
