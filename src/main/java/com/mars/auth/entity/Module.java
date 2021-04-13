package com.mars.auth.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Module extends BaseEntity{
	@Id
	private String code;
	
	private String name;
	private String description;
	
	@OneToMany(
	        mappedBy = "module",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Segments> segments = new  ArrayList<>();
	
	@OneToMany(
	        mappedBy = "module",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<ModuleFunctions> functions = new  ArrayList<>();
	
	@OneToMany(
	        mappedBy = "module",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Menus> menus = new  ArrayList<>();
}
