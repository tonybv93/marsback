package com.mars.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mars.auth.dto.MenuDTO;
import com.mars.auth.dto.ModuleDTO;
import com.mars.auth.dto.SegmentDTO;
import com.mars.auth.entity.Users;
import com.mars.auth.service.IModuleService;
import com.mars.shared.OperationResponseDTO;

@RestController
public class ModulesConfigController {

	@Autowired IModuleService moduleServ;
	
	@GetMapping("/modules")
	public List<ModuleDTO> getAllModules(Authentication auth){
		Users u = (Users) auth.getPrincipal();
		return moduleServ.getModuleData(u);
	}
	
	// AGREGAR O EDITAR MODULO
	@PostMapping("/module")
	public OperationResponseDTO saveModule(@RequestBody MenuDTO dto, Authentication auth) {
		Users u = (Users) auth.getPrincipal();
		return moduleServ.saveModule(dto, u);
	}
	
	// AGREGAR O EDITAR MENU
	@PostMapping("/menu")
	public OperationResponseDTO saveMenu(@RequestBody MenuDTO dto, Authentication auth) {
		Users u = (Users) auth.getPrincipal();
		return moduleServ.saveMenu(dto, u);
	}
	
	// AGREGAR O EDITAR SEGMENTO
	@PostMapping("/segment")
	public OperationResponseDTO saveSegment(@RequestBody SegmentDTO dto, Authentication auth) {
		Users u = (Users) auth.getPrincipal();
		return moduleServ.saveSegment(dto, u);
	}
	
	// AGREGAR O EDITAR FUNCION
	@PostMapping("/function")
	public OperationResponseDTO saveFunction(@RequestBody SegmentDTO dto, Authentication auth) {
		Users u = (Users) auth.getPrincipal();
		return moduleServ.saveFunction(dto, u);
	}
}
