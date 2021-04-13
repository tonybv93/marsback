package com.mars.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PickListDTO {
	public String code;
	public Integer id;
	public String name;
	public String description;
}
