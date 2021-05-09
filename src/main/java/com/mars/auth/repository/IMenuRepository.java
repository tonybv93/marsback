package com.mars.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mars.auth.entity.Menu;

@Repository
public interface IMenuRepository extends JpaRepository<Menu, String> {
	@Query(nativeQuery = true, value = "select mm.* from user_menus um left join sy_menu mm ON um.code_menu = mm.code where um.id_user = ?1 and mm.enable = 1" )
	List<Menu> getUserMenus(int idUser);
}
