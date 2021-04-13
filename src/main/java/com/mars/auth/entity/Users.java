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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mars.shared.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Users extends BaseEntity implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	// VIEWS: FRONT GUARDS
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="user_views",
			joinColumns = @JoinColumn(name="id_user"), 
			inverseJoinColumns = @JoinColumn(name="id_view"))
	private Set<Views> views;
	
	// AUTHORITIES: BACK PREAUTHORIZED
	@OneToMany(
			fetch = FetchType.EAGER,
	        mappedBy = "user",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private Set<UserAuthorities> auths;
	
	// SEGMENTS: DATA FILTER
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name="user_segments",
			joinColumns = @JoinColumn(name="id_user"), 
			inverseJoinColumns = @JoinColumn(name="segment_code"))
	private Set<Segments> segments;
	
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
