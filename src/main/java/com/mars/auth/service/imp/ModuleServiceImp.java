package com.mars.auth.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mars.auth.dto.MenuDTO;
import com.mars.auth.dto.ModuleDTO;
import com.mars.auth.dto.SegmentDTO;
import com.mars.auth.entity.Function;
import com.mars.auth.entity.Menu;
import com.mars.auth.entity.Module;
import com.mars.auth.entity.Segment;
import com.mars.auth.entity.Users;
import com.mars.auth.repository.IFunctionRepository;
import com.mars.auth.repository.IMenuRepository;
import com.mars.auth.repository.IModuleRepository;
import com.mars.auth.repository.ISegmentRepository;
import com.mars.auth.service.IModuleService;
import com.mars.shared.OperationResponseDTO;

@Service
public class ModuleServiceImp implements IModuleService{

	@Autowired IModuleRepository moduleRepo;
	@Autowired IMenuRepository menuRepo;
	@Autowired ISegmentRepository segmentRepo;
	@Autowired IFunctionRepository functionRepo;
	
	@Override
	public List<ModuleDTO> getModuleData(Users u) {
		List<ModuleDTO> response = new ArrayList<>();
		List<Module> modules = moduleRepo.findAll();
		for (Module m: modules){
			response.add(
				new ModuleDTO(
						m.getCode(), 
						m.getName(),
						m.getMOrder(),
						m.getIcon(),
						m.isEnable(),						
						m.getSegments().stream()
							.map( s->new SegmentDTO( s.getCode(),s.isEnable(),s.getName(),s.getDatafilter(),m.getCode() ) )
							.collect(Collectors.toList()), 
						m.getFunctions()
							.stream()
							.map( f->new SegmentDTO( f.getCode(),f.isEnable(),f.getName(),null,f.getCode() ) )
							.collect(Collectors.toList()),
						m.getMenus().stream()
							.map( sm-> new MenuDTO( sm.getCode(), sm.getName(), sm.getMOrder(), sm.getIcon(), sm.getMType(), sm.getLink(), null, sm.isEnable(), m.getCode() ) )
							.collect(Collectors.toList())
						));
		}
		return response;
	}


	@Override
	public OperationResponseDTO saveModule(MenuDTO dto, Users u) {
		Module xm = moduleRepo.findById(dto.getCode()).orElse(null); 
		Module m;
		// AGREGAR
		if (dto.getModule().equals("ADD")) {
			if (xm != null) {
				return new OperationResponseDTO(null, null, false, "Este código ya está en uso");				
			}			
			m = new Module(dto.getCode(), dto.getName(), dto.getIcon(), dto.getOrder(), true, 0, 0, new  ArrayList<>(), new  ArrayList<>(), new  ArrayList<>());
		// EDITAR
		}else {
			if (xm == null)  throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			m = xm;			
			m.setName(dto.getName());
			m.setEnable(dto.isEnable());
			m.setIcon(dto.getIcon());
			m.setMOrder(dto.getOrder());			
		}
		try {
			m = moduleRepo.save(m);
			return new OperationResponseDTO(null,m.getCode(),true, null);
		} catch (Exception e) {
			return new OperationResponseDTO(null,null,false, e.getMessage());
		}
	}

	@Override
	public OperationResponseDTO saveMenu(MenuDTO dto, Users u) {
		// DEFINIENDO CÓDIGO
		if (dto.getCode() == null) {
			Module m = moduleRepo.findById(dto.getModule()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND) );
			m.setAutoincMenu(m.getAutoincMenu() + 1);
			m = moduleRepo.save(m);
			dto.setCode( m.getCode() + "-" + String.format("%03d", m.getAutoincMenu() ));			
		}
		
		Menu mm = new Menu(dto.getCode(), dto.getName(), dto.getIcon(), dto.getLink(), dto.getOrder(), "CHILD", moduleRepo.getOne(dto.getModule()) , dto.isEnable());
		try {
			menuRepo.save(mm);
			return new OperationResponseDTO(null,mm.getCode(),true, null);
		} catch (Exception e) {
			return new OperationResponseDTO(null,null,false, e.getMessage());
		}
	}


	@Override
	public OperationResponseDTO saveSegment(SegmentDTO dto, Users u) {
		// DEFINIENDO CÓDIGO
		if (dto.getCode() == null) {
			Module m = moduleRepo.findById(dto.getModule()).orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND) );
			m.setAutoincSeg(m.getAutoincSeg() + 1);
			m = moduleRepo.save(m);
			dto.setCode( m.getCode() + "-" + String.format("%03d", m.getAutoincSeg() ));			
		}
		
		Segment ms = new Segment(dto.getCode(), dto.isEnable(), dto.getName(), dto.getDatafilter(), dto.getModule());
		try {
			segmentRepo.save(ms);
			return new OperationResponseDTO(null,ms.getCode(),true, null);
		} catch (Exception e) {
			return new OperationResponseDTO(null,null,false, e.getMessage());
		}
	}

	@Override
	public OperationResponseDTO saveFunction(SegmentDTO dto, Users u) {
		Function mf = new Function(dto.getCode(), dto.isEnable(), dto.getName(), dto.getModule());
		try {
			functionRepo.save(mf);
			return new OperationResponseDTO(null,mf.getCode(),true, null);
		} catch (Exception e) {
			return new OperationResponseDTO(null, null, false, e.getMessage());
		}
	}

}
