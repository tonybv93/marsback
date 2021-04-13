package com.mars.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import com.mars.auth.entity.Users;
import com.mars.auth.repository.IUsersRepository;

import io.jsonwebtoken.Claims;

@Component
public class JWTFilter extends OncePerRequestFilter{
	private static final  String AUTH_HEADER = "Authorization";
	private static final String PREFIX = "Bearer ";
	

	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private IUsersRepository usersRepo;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String jwtToken = null;
		String tokenusername  = null;
		final String requestTokenHeader = request.getHeader(AUTH_HEADER);
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith(PREFIX)) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				tokenusername = jwtUtil.getUsernameFromToken(jwtToken);
				if (tokenusername != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					Users user = usersRepo.findByUsername(tokenusername).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
					if (!jwtUtil.isTokenExpired(jwtToken)) {
						Claims claims = jwtUtil.getAllClaimsFromToken(jwtToken);
						setUpSpringAuthentication(claims, user);
					}
				}				
			} catch (Exception e) {
				System.out.println("Filter error: " + e.getMessage());
			}
		}
		filterChain.doFilter(request, response);	
	}
	
	
	private void setUpSpringAuthentication(Claims claims, Users user) {
		List<GrantedAuthority> grantedAuthorities =  new ArrayList<>(user.getAuthorities());
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				user, 
				null,
				grantedAuthorities
		);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
}
