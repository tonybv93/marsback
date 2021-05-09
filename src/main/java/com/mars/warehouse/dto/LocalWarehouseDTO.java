package com.mars.warehouse.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LocalWarehouseDTO {
	private Integer id;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String location;
	private List<WarehouseDTO> warehouses;
}
