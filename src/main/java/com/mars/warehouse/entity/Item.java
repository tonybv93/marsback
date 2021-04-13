package com.mars.warehouse.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.mars.shared.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Table(name = "st_item")
public class Item extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	private String name;
	private String description;
	private String skuCode;
	private String model;
	private String brand;
	private String distributor;
	private String color;
	private String photoUri;
	private String itemVersion;
	private String packaging;
	private Integer calification;
	private String specifications;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sub_category")
	private ItemSubcategory subCategory;
}
