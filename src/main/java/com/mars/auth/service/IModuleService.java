package com.mars.auth.service;

import java.util.List;

import com.mars.auth.dto.MenuDTO;
import com.mars.auth.dto.ModuleDTO;
import com.mars.auth.dto.SegmentDTO;
import com.mars.auth.entity.Users;
import com.mars.shared.OperationResponseDTO;
public interface IModuleService {
	
	// AUTHORITIES SISTEM
	public List<ModuleDTO> getModuleData(Users u);
	
	// MODULOS Y MENUS
	public OperationResponseDTO saveModule(MenuDTO dto, Users u);
	public OperationResponseDTO saveMenu(MenuDTO dto, Users u);
	
	// SEGMENTOS
	public OperationResponseDTO saveSegment(SegmentDTO dto, Users u);
	public OperationResponseDTO saveFunction(SegmentDTO dto, Users u);
}
