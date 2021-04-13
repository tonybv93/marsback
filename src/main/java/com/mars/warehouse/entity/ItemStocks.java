package com.mars.warehouse.entity;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "st_item_stocks")
public class ItemStocks extends BaseEntity{
	@EmbeddedId
	private ItemStocksKey id;
	
	private BigDecimal inWarehouse;
	private BigDecimal reserved;
	private BigDecimal usableStock;
}
