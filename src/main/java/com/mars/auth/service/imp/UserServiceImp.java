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
import com.mars.auth.entity.Menus;
import com.mars.auth.entity.Users;
import com.mars.auth.entity.Views;
import com.mars.auth.repository.IUsersRepository;
import com.mars.auth.repository.IViewMenuRepository;
import com.mars.auth.repository.IViewsRepository;
import com.mars.auth.service.IUserService;
import com.mars.config.JWTUtil;

@Service
public class UserServiceImp implements IUserService {

	@Autowired private JWTUtil jwtUtil;
	@Autowired private BCryptPasswordEncoder encoder;
	@Autowired private IUsersRepository userRepo;
	@Autowired private IViewsRepository viewRepo;
	@Autowired private IViewMenuRepository viewmenuRepo;
	
	
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
		List<MenuDTO> menus = new ArrayList<>();
		List<MenuDTO> submenus = new ArrayList<>();
		List<Views> views = viewRepo.findViewsByUser(u.getId());
		
		for(Views v:views) {
			viewmenuRepo.findAllByViewAndEnable(v, true).forEach( rm ->{
				Menus menu = rm.getMenu();
				if (rm.getMenu().getTypem().equals("CHILD")) {
					submenus.add(
						new MenuDTO(menu.getCode(), menu.getName(), menu.getOrderm(), menu.getIcon(),
								menu.getParentCode(),"CHILD", menu.getLink(),null)
					);
				}else { // PARENT & ALONE
					menus.add(
						new MenuDTO(menu.getCode(), menu.getName(), menu.getOrderm(), menu.getIcon(),
								null,menu.getTypem(), menu.getLink(),new ArrayList<>())
					);
				}
			});	
		}
		
		// BORRAR DUPLICADOS
		List<MenuDTO> unqMenus = menus.stream().distinct().collect(Collectors.toList());
		List<MenuDTO> unqSubmenus = submenus.stream().distinct().collect(Collectors.toList());
		
		// AGREGAR HIJOS A PADRES
		for (MenuDTO sm:unqMenus) {
			if (sm.getType().equals("PAREN"))
				sm.getSubmenus().addAll(
						unqSubmenus.stream().filter(p-> p.getParentCode().equals(sm.getCode()))
							.collect(Collectors.toList())
						);
		}
		return unqMenus.stream().sorted(Comparator.comparing(MenuDTO::getOrder)).collect(Collectors.toList());
	}

}
