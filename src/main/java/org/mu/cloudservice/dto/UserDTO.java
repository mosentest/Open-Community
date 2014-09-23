package org.mu.cloudservice.dto;

import org.mu.cloudservice.entity.User;

public class UserDTO implements DataTransferObject<User> {

	private String account;
	
	private String email;
	
	private String password;
	
	private String confirm;
	
	private String captcha;
	
	@Override
	public User toObject() {
		User user = new User();
		user.setAccount(account);
		user.setEmail(email);
		user.setPassword(password);
		return user;
	}

	@Override
	public UserDTO toDTO(User object) {
		return null;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
