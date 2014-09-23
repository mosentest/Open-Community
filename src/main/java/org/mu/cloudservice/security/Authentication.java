package org.mu.cloudservice.security;

import java.util.Collection;

import org.mu.cloudservice.entity.User;
import org.springframework.security.core.GrantedAuthority;

public class Authentication extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 6978257266007790166L;
	
	private User user;

	public Authentication(User user, String username, String password,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.user = user;
	}
	
	public long getId() {
		return user.getId();
	}
	
	public String getProfile() {
		return user.getProfile();
	}
	
	public User getUser() {
		return user;
	}
	
	public String getAccount() {
		return user.getAccount();
	}

}
