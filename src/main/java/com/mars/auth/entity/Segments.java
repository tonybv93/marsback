package com.mars.auth.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mars.shared.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Segments extends BaseEntity{
	@Id
	private String code;
	private String name;
	private String description;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module")
	private Module module;
}
