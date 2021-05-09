package com.mars.auth.service.imp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mars.auth.dto.MenuDTO;
import com.mars.auth.dto.RequestLogin;
import com.mars.auth.dto.ResponseLogin;
import com.mars.auth.dto.UserDTO;
import com.mars.auth.entity.Menu;
import com.mars.auth.entity.Users;
import com.mars.auth.repository.IMenuRepository;
import com.mars.auth.repository.IUsersRepository;
import com.mars.auth.service.IUserService;
import com.mars.config.JWTUtil;
import com.mars.shared.OperationResponseDTO;
import com.mars.shared.PageResponseDTO;
import com.mars.shared.PickListDTO;

@Service
public class UserServiceImp implements IUserService {

	@Autowired private JWTUtil jwtUtil;
	@Autowired private BCryptPasswordEncoder encoder;
	@Autowired private IUsersRepository userRepo;
	@Autowired private IMenuRepository menuRepo;
	
	
	@Override
	public ResponseLogin login(RequestLogin dto) {
		Users user = userRepo.findByUsername(dto.getUsername()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		if (encoder.matches(dto.getPassword(), user.getPassword()) ) {
			if (user.isEnable()) {	
				String token = jwtUtil.generateToken(user);
				return new ResponseLogin(true,token,"OK");
			}else {
				return new ResponseLogin(false,null,"Usuario inactivo");
			}
		}else {
			return new ResponseLogin(false,null,"Credenciales incorrectas");
		}
	}

	@Override
	public List<MenuDTO> getUserMenus(Users u) {
		List<Menu> menus = menuRepo.getUserMenus(u.getId());
		List<MenuDTO> modules = new ArrayList<>();
		// Reconocer los padres
		for(Menu m : menus) {			
			if (modules.stream().noneMatch( p->p.getCode().equals(m.getModule().getCode()) ) )				
				modules.add(new MenuDTO(
						m.getModule().getCode(), 
						m.getModule().getName(),
						m.getModule().getMOrder(), 
						m.getModule().getIcon(), 
						"PAREN", 
						"", 
						new ArrayList<>(),
						true,null));
		}
		// Agregar hijos
		for (MenuDTO module : modules) {			
			module.getSubmenus().addAll(					
					menus.stream()
					.filter(p-> p.getModule().getCode().equals(module.getCode()))
					.sorted(Comparator.comparing(Menu::getMOrder))
					.map(m -> new MenuDTO(m.getCode(), m.getName(), m.getMOrder(), m.getIcon(), m.getMType(), m.getLink(), null, true,null))
					.collect(Collectors.toList())
			);			
		}			
		return modules;
		
	}

	@Override
	public OperationResponseDTO changePassword(String old, String newpass, Users u) {
		try {			
			if (encoder.matches(old, u.getPassword())) {
				u.setPassword(encoder.encode(newpass));
				userRepo.save(u);
				return new OperationResponseDTO(null, null, true, "La contraseña se cambió correctamente.");
			}
			return new OperationResponseDTO(null, null, false, "La contraseña es incorrecta.");
		} catch (Exception e) {
			return new OperationResponseDTO(null, null, false, e.getMessage());
		}
	}

	@Override
	public PageResponseDTO<UserDTO> getAllUsers(String filter, String bu, int status, int page, int size) {
		// TODO: Crear SP User list
		return new PageResponseDTO<>(page, size, 0, new ArrayList<UserDTO>());
	}

	@Override
	public List<PickListDTO> searchUsers(String filter) {
		return null;
	}

}
