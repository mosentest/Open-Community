package org.mu.community.common.security;

import java.util.Collection;

import org.mu.community.common.entity.User;
import org.springframework.security.core.GrantedAuthority;

public class Authentication extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 6978257266007790166L;
	
	private User user;
	
	private boolean expired;
	
	private String latestMessage;

	public Authentication(User user, String username, String password,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.user = user;
		this.expired = false;
	}
	
	public long getId() {
		return user.getId();
	}
	
	public String getPath() {
		return user.getPath();
	}
	
	public String getProfile() {
		return user.getProfile();
	}
	
	public void invalidate() {
		expired = true;
	}
	
	public User getUser() {
		return user;
	}
	
	public String getAccount() {
		return user.getAccount();
	}
	
	public boolean isExpired() {
		return expired;
	}

	public String getLatestMessage() {
		return latestMessage;
	}

	public void setLatestMessage(String latestMessage) {
		this.latestMessage = latestMessage;
	}

}
