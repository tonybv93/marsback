package com.mars.auth.service;

import java.util.List;

import com.mars.auth.dto.MenuDTO;
import com.mars.auth.dto.RequestLogin;
import com.mars.auth.dto.ResponseLogin;
import com.mars.auth.dto.UserDTO;
import com.mars.auth.entity.Users;
import com.mars.shared.OperationResponseDTO;
import com.mars.shared.PageResponseDTO;
import com.mars.shared.PickListDTO;

public interface IUserService {
	public ResponseLogin login(RequestLogin dto);
	public OperationResponseDTO changePassword(String old, String newpass, Users u);
	public List<MenuDTO> getUserMenus(Users u);
	public PageResponseDTO<UserDTO> getAllUsers(String filter, String bu, int status, int page, int size );	
	public List<PickListDTO> searchUsers(String filter);
}
