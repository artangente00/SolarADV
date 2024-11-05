package com.PlayGroundAdv.Solar.security;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserDetailImpl implements UserDetails {

	private final String userName;
	private final String password;
	private final boolean enabled;
	private final List<GrantedAuthority> authorities;

	public UserDetailImpl(AuthentificationEntity user) {
		this.userName = user.getEmail();
		this.password = user.getPassword();
		this.enabled = user.getActive();
		this.authorities = Collections.singletonList(
				new SimpleGrantedAuthority(user.getRoleEntity().getDescription()));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
}
