package com.mars.warehouse.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mars.auth.entity.Users;
import com.mars.shared.OperationResponseDTO;
import com.mars.shared.PickListDTO;
import com.mars.warehouse.dto.LocalWarehouseDTO;
import com.mars.warehouse.dto.WarehouseDTO;
import com.mars.warehouse.repository.ILocalRepository;
import com.mars.warehouse.repository.IWarehouseRepository;
import com.mars.warehouse.service.IWarehouseService;

@Service
public class WarehouseServiceImp implements IWarehouseService{

	@Autowired IWarehouseRepository repo;
	@Autowired ILocalRepository localsrepo;
	
	@Override
	public List<PickListDTO> getWarehouseSuggestions(String text) {
		return repo.findTop5ByNameContainingAndEnable(text, true).stream()
				.map(p-> new PickListDTO(null, p.getId(), p.getName(), p.getDescription()))
				.collect(Collectors.toList());
	}

	@Override
	public List<WarehouseDTO> getWarehouseList() {
		return repo.findByEnable(true).stream().map(p-> new WarehouseDTO
				(p.getId(), p.getName(), p.getDescription(), 
						p.getType().getName(), p.getLocal().getName(), p.getType().getId(), p.getLocal().getId()))
				.collect(Collectors.toList());
	}

	@Override
	public OperationResponseDTO saveWarehouse(WarehouseDTO dto, Users u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LocalWarehouseDTO> getLocalWarehouses() {
		List<WarehouseDTO> whs = repo.findByEnable(true).stream().map(p-> new WarehouseDTO
				(p.getId(), p.getName(), p.getDescription(), 
						p.getType().getName(), p.getLocal().getName(), p.getType().getId(), p.getLocal().getId()))
				.collect(Collectors.toList());
		
		return localsrepo.findByEnable(true).stream()
				.map(l -> new LocalWarehouseDTO(l.getId(), l.getName(), l.getAddress(), l.getPhone(), l.getEmail(), l.getLocation(), 
						whs.stream().filter(p-> p.getIdlocal() == l.getId()).collect(Collectors.toList()) ))
				.collect(Collectors.toList());
	}

}
