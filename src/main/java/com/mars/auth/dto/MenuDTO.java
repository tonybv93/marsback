package com.mars.auth.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MenuDTO {
	private String code;
	private String name;
	private int order;
	private String icon;
	private String type; 
	private String link; 
	private List<MenuDTO> submenus = new ArrayList<>();
	private boolean enable;
	private String module;
}
