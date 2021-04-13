package com.mars.warehouse.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "st_item_category")
public class ItemCategory extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	private String name;
	private String description;
	private String imguri;
	
	@OneToMany(
			fetch = FetchType.EAGER,
	        mappedBy = "category",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<ItemSubcategory> subcategories = new ArrayList<>();
	
}
