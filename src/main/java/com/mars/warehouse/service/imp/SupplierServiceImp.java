package com.mars.warehouse.service.imp;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mars.auth.entity.Users;
import com.mars.shared.OperationResponseDTO;
import com.mars.shared.PageResponseDTO;
import com.mars.shared.PickListDTO;
import com.mars.warehouse.dto.SupplierDTO;
import com.mars.warehouse.entity.Supplier;
import com.mars.warehouse.repository.ISupplierRepository;
import com.mars.warehouse.service.ISupplierService;

@Service
public class SupplierServiceImp implements ISupplierService {

	@Autowired ISupplierRepository suprepo;
	
	@Override
	public List<PickListDTO> getSupplierSuggestion(String text) {
		return suprepo.findTop5ByNameContainingAndEnable(text,true).stream()
				.map(p-> new PickListDTO(null, p.getId(), p.getName(), p.getLegalId() ))
				.collect(Collectors.toList());
	}

	@Override
	public SupplierDTO getSupplier(int id) {
		return suprepo.findById(id).map( p -> new SupplierDTO(
				p.getId(),
				p.getName(), 
				p.getLegalId(), 
				p.getLegalName(), 
				p.getAddress(), 
				p.getCity(), 
				p.getEmail(),
				p.getPhone(), 
				p.getCel(),
				p.getWebpage(), 
				p.getContactPerson()) )
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@Override
	public PageResponseDTO<SupplierDTO> getSupplierList(String filter, int page, int size) {
		return new PageResponseDTO<>(
				page, 
				size, 
				0, 
				suprepo.getSupplierList("%" + filter + "%", page*size, size).stream()
				.map(p-> new SupplierDTO(
						p.getId(), 
						p.getName(),
						p.getLegalid(),
						p.getLegalname(), 
						p.getAddress(),
						p.getCity(),
						p.getEmail(),
						p.getPhone(),
						p.getCel(),
						p.getWebpage(),
						p.getContact())).collect(Collectors.toList())
				);
	}

	@Override
	public OperationResponseDTO saveSupplier(SupplierDTO dto, Users u) {
		Supplier supp = null;
		try {
			if (dto.getId() == null) {
				supp = new Supplier(
						null, 
						dto.getName(), 
						dto.getLegalid(),
						dto.getLegalname(), 
						dto.getAddress(),
						dto.getCity(),
						dto.getEmail(),
						dto.getPhone(),
						dto.getCel(),
						dto.getWebpage(),
						dto.getContact());
				supp.setCreatedBy(u.getUsername());
				
			}else {
				supp = suprepo.findById(dto.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND) );
				supp.setName(dto.getName()); 
				supp.setLegalId(dto.getLegalid());
				supp.setLegalName(dto.getLegalname()); 
				supp.setAddress(dto.getAddress());
				supp.setCity(dto.getCity());
				supp.setEmail(dto.getEmail());
				supp.setPhone(dto.getPhone());
				supp.setWebpage(dto.getWebpage());
				supp.setContactPerson(dto.getContact());
				
				supp.setUpdatedBy(u.getUsername());
				supp.setUpdatedAt(new Date());
			}		
			supp = suprepo.save(supp);
			return new OperationResponseDTO(supp.getId(), null, true, null);			
		} catch (Exception e) {
			return new OperationResponseDTO(null, null, false, e.getMessage());
		}
	}
}
