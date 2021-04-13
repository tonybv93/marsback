package com.mars.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mars.warehouse.entity.Local;

@Repository
public interface ILocalRepository extends JpaRepository<Local, Integer>{

}
