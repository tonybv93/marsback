package com.mars.auth.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "sy_module")
public class Module{
	@Id
	private String code;
	
	private String name;
	private String icon;
	private int mOrder;
	private boolean enable;
	private int autoincMenu;
	private int autoincSeg;
	
	@OneToMany(
	        mappedBy = "module",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Segment> segments = new  ArrayList<>();
	
	@OneToMany(
	        mappedBy = "module",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Function> functions = new  ArrayList<>();
	
	@OneToMany(
	        mappedBy = "module",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Menu> menus = new  ArrayList<>();
}
