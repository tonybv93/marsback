package com.mars.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WarehouseDTO {
	private Integer id;
	private String name;
	private String description;
	private String type;
	private String local;
	private Integer idtype;
	private Integer idlocal;
}
