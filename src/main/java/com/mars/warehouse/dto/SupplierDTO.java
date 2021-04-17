package com.mars.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SupplierDTO {
	private Integer id;
	private String name;	
	private String legalId;	
	private String legalName;	
	private String address;
	private String city;
	private String email;
	private String phone;
	private String webpage;
	private String contactPerson;
}
