package com.mars.warehouse.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemCategoryDTO {
	private Integer id;
	private String name;
	private String description;
	private String img;
	private Integer parentid;
	private List<ItemCategoryDTO> subcategories;
}
