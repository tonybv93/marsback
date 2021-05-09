package com.mars.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mars.auth.entity.Function;

@Repository
public interface IFunctionRepository extends JpaRepository<Function, String> {

}
