package com.mars.auth.service;

import java.util.List;

import com.mars.auth.dto.MenuDTO;
import com.mars.auth.dto.RequestLogin;
import com.mars.auth.dto.ResponseLogin;
import com.mars.auth.entity.Users;

public interface IUserService {
	public ResponseLogin login(RequestLogin dto);
	public List<MenuDTO> getUserMenus(Users u);
}
