package com.mars.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mars.warehouse.entity.ItemStocks;
import com.mars.warehouse.entity.ItemStocksKey;

@Repository
public interface IStocksRepository extends JpaRepository<ItemStocks, ItemStocksKey>{

}
