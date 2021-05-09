package com.mars.auth.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ModuleDTO {
	private String code;
	private String name;
	private int order;
	private String icon;
	private boolean enable;
	private List<SegmentDTO> segments = new ArrayList<>();
	private List<SegmentDTO> functions = new ArrayList<>();
	private List<MenuDTO> menus = new ArrayList<>();
}
