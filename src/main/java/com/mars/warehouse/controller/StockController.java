package com.mars.warehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mars.warehouse.dto.LocalWarehouseDTO;
import com.mars.warehouse.service.IWarehouseService;

@RestController
@RequestMapping("/wah")
public class StockController {
	
	@Autowired IWarehouseService whservice;
	
	@GetMapping("/locals/warehouses")
	public List<LocalWarehouseDTO> getLocals(){
		return whservice.getLocalWarehouses();
	}
}
