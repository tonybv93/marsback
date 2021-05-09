package com.mars.warehouse.controller;

import java.util.List;

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
import com.mars.shared.PickListDTO;
import com.mars.warehouse.dto.WarehouseDTO;
import com.mars.warehouse.service.IWarehouseService;

@RestController
@RequestMapping("/wah")
public class WarehouseController {

	@Autowired IWarehouseService whserv;
	
	@GetMapping("/warehouse/suggest")
	public List<PickListDTO> getWarehouseSuggestions(@RequestParam String filter) {
		return whserv.getWarehouseSuggestions(filter);
	}
	
	@GetMapping("/warehouses")
	public List<WarehouseDTO> getWarehouseList(){
		return whserv.getWarehouseList();
	}
	
	@PostMapping("/warehouse")
	public OperationResponseDTO saveWarehouse(@RequestBody WarehouseDTO dto, Authentication auth) {
		Users u = (Users) auth.getPrincipal();
		return whserv.saveWarehouse(dto, u);
	}
}
