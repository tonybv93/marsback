package com.mars.warehouse.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class ItemStocksKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idItem;
	private int idWarehouse;
}
