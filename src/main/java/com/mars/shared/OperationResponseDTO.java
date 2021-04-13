package com.mars.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OperationResponseDTO {
	public Integer id;
	public String code;
	public boolean status;
	public String message;
}
