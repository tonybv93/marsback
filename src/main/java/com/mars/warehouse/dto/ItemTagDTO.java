package com.mars.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemTagDTO {
	private Integer id;
	private String name;
	private String description;
	private String color;
	private String category;
}
