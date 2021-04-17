package com.mars.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mars.warehouse.dto.SupplierDTO;
import com.mars.warehouse.entity.Supplier;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Integer>{
	
	@Query(nativeQuery = true, value = "call sp_supplier_list(?1, ?2, ?3)")
	List<SupplierDTO> getSupplierList(String filter,int offset, int size);
	
	List<Supplier> findTop5ByNameContaining(String text);
	
}
