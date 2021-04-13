package com.mars.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mars.auth.entity.Views;

@Repository
public interface IViewsRepository extends JpaRepository<Views, Integer>{

	@Query(value = "select v.* from user_views uv left join views v on uv.id_view = v.id where uv.id_user = ?1", nativeQuery = true)
	public List<Views> findViewsByUser(int userid);
}
