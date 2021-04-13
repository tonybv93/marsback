package com.mars.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mars.auth.dto.MenuDTO;
import com.mars.auth.dto.RequestLogin;
import com.mars.auth.dto.ResponseLogin;
import com.mars.auth.entity.Users;
import com.mars.auth.service.IUserService;

@RestController
public class AuthController {
	
	@Autowired private BCryptPasswordEncoder encoder;
	@Autowired private IUserService userSer;
	
	@GetMapping("/encode")
	public String encode(@RequestParam String text) {		
		return  encoder.encode(text);
	}
	
	@PostMapping("/login")
	public ResponseLogin login(@RequestBody RequestLogin dto) {
		return userSer.login(dto);
	}
	
	@GetMapping("/menus")
	public List<MenuDTO> getMenus(Authentication auth) {
		Users u = (Users) auth.getPrincipal();
		return  userSer.getUserMenus(u);
	}
	
}
