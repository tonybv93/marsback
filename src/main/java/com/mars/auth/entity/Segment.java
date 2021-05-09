package com.mars.auth.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sy_segment")
public class Segment{
	@Id
	private String code;
	private boolean enable;
	private String name;
	private String datafilter;	
	private String module;
}
