package com.mars.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mars.auth.entity.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
public static final long JWT_TOKEN_VALIDITY = 60 * 1000 * 60 * 12; //Último bloque: Nro de minutos
	
	@Value("${jwt.secret}")
	private String secret;
	
	// OBTENER USUARIO 
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Boolean isTokenExpired(String token) {
		final Date expiration =  getClaimFromToken(token, Claims::getExpiration);
		return expiration.before(new Date());
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	// OBTENER TODOS LOS CLAIMS
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}
	
	// GENERAR TOKEN PARA USUARIO
	public String generateToken(Users user) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, user);
	}
	
	// GENERAR TOKEN
	private String doGenerateToken(Map<String, Object> claims, Users user) {
		//List<GrantedAuthority> grantedAuthorities =  new ArrayList<>(user.getAuthorities());
		return Jwts.builder()
				.setClaims(claims)
				.claim("id", user.getId())
				.claim("displayname", user.getDisplayName())
				.claim("title", user.getSubtitle())
				.claim("phuri", user.getPhotoUri())
				.claim("expired", user.isCredentialExpired())
				.claim("gen", user.getGender())
				.claim("ap", user.isAcceptedPolicies())
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY ))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}
	
}
