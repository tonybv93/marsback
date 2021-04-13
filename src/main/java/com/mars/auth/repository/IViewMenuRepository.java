package com.mars.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mars.auth.entity.ViewMenus;
import com.mars.auth.entity.Views;

@Repository
public interface IViewMenuRepository extends JpaRepository<ViewMenus, Integer>{
	public List<ViewMenus> findAllByViewAndEnable(Views v, boolean enable);
}
