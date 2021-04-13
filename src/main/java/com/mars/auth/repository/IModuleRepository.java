package com.mars.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mars.auth.entity.Module;

@Repository
public interface IModuleRepository extends JpaRepository<Module, String>{
	
}
