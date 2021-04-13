package com.mars.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SPCategoryDetailsDTO {
	Integer catid;
	String catname;
	Integer subid;
	String subname;
}
