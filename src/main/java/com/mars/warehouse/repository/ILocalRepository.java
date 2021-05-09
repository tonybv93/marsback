package com.mars.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mars.warehouse.entity.Local;

@Repository
public interface ILocalRepository extends JpaRepository<Local, Integer>{
	List<Local> findByEnable(boolean enable);
}
