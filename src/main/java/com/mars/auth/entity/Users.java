package com.mars.auth.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mars.shared.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Users extends BaseEntity implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	private String username;
	private String password;
	private String displayName;
	private String subtitle;
	private String gender;
	private boolean hasPhoto;
	private String photoUri;
	
	private String lastPassword;
	private int loginTries;
	private String recoveryCode;
	private boolean credentialExpired;
	private boolean acceptedPolicies;

	// AUTHORITIES: BACK PREAUTHORIZED
	@OneToMany(
			fetch = FetchType.EAGER,
	        mappedBy = "user",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private Set<UserFunctions> auths;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		auths.forEach(r -> {
			authorities.add(new SimpleGrantedAuthority( r.getFunctionCode() + '-' +r.getAccessLvl()  ));
		});
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !credentialExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return enable;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialExpired;
	}

	@Override
	public boolean isEnabled() {
		return enable;
	}

	
}
