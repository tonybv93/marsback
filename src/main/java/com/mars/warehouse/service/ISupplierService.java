package com.mars.warehouse.service;

import java.util.List;

import com.mars.auth.entity.Users;
import com.mars.shared.OperationResponseDTO;
import com.mars.shared.PageResponseDTO;
import com.mars.shared.PickListDTO;
import com.mars.warehouse.dto.SupplierDTO;

public interface ISupplierService {
	public List<PickListDTO> getSupplierSuggestion(String text);
	public SupplierDTO getSupplier(int id);
	public PageResponseDTO<SupplierDTO> getSupplierList(String filter, int page, int size);
	public OperationResponseDTO saveSupplier( SupplierDTO dto, Users u );
}