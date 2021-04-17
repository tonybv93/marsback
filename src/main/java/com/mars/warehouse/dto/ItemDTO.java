package com.mars.warehouse.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemDTO {
	public Integer id;
	private String name;
	private String description;
	private String skuCode;
	private String model;
	private String brand;
	private String distributor;
	private String color;
	private String packaging;
	private String specifications;
	
	private Integer calification;
	private String version;
	private String photoUri;

	private CategoryDetailsDTO category;
	private List<ItemTagDTO> tags = new ArrayList<>();
}
