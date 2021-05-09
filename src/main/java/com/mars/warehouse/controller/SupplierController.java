package com.mars.warehouse.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mars.auth.entity.Users;
import com.mars.shared.OperationResponseDTO;
import com.mars.shared.PageResponseDTO;
import com.mars.shared.PickListDTO;
import com.mars.warehouse.dto.SupplierDTO;
import com.mars.warehouse.service.ISupplierService;

@RestController
@RequestMapping("/wah")
public class SupplierController {
	
	@Autowired ISupplierService suppserv;
	
	@GetMapping("/supplier/suggest")
	public List<PickListDTO> getSupplierSuggestions(@RequestParam String filter) {
		return suppserv.getSupplierSuggestion(filter);
	}
	
	@GetMapping("/suppliers")
	public PageResponseDTO<SupplierDTO> getSupplierList(
			@RequestParam int page, 
			@RequestParam int size,
			@RequestParam Optional<String> filter ){
		return suppserv.getSupplierList(filter.orElse(""), page, size);
	}
	
	@PostMapping("/supplier")
	public OperationResponseDTO saveSupplier(@RequestBody SupplierDTO dto, Authentication auth) {
		Users u = (Users) auth.getPrincipal();
		return suppserv.saveSupplier(dto, u);
	}
}
