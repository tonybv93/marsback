package com.mars.warehouse.service;

import java.util.List;

import com.mars.auth.entity.Users;
import com.mars.shared.OperationResponseDTO;
import com.mars.shared.PickListDTO;
import com.mars.warehouse.dto.LocalWarehouseDTO;
import com.mars.warehouse.dto.WarehouseDTO;

public interface IWarehouseService {
	public List<PickListDTO> getWarehouseSuggestions(String text);
	public List<WarehouseDTO> getWarehouseList();
	public OperationResponseDTO saveWarehouse(WarehouseDTO dto, Users u);	
	
	public List<LocalWarehouseDTO> getLocalWarehouses();
}
