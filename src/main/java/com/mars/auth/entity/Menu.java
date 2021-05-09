package com.mars.auth.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "sy_menu")
public class Menu{
	@Id
	private String code;
	private String name;
	private String icon;
	private String link;
	private int mOrder;
	private String mType;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "module")
	private Module module;
	private boolean enable;
}
