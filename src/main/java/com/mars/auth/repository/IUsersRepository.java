package com.mars.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mars.auth.entity.Users;

@Repository
public interface IUsersRepository extends JpaRepository<Users, Integer>{
	public Optional<Users> findByUsername(String username);
}
