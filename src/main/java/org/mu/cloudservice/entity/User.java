package org.mu.cloudservice.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {

	private static final long serialVersionUID = -3018705859017013707L;

	private long id;
	
	private String email;
	
	private String account;
	
	private String password;
	
	private boolean verified;
	
	private String path;
	
	private String profile;
	
	private Date create;
	
	private Date update;
	
	private int nFriends;
	
	private int nFans;

	private List<Role> roles;
	
	public User() {}

	public User(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public int getnFriends() {
		return nFriends;
	}

	public void setnFriends(int nFriends) {
		this.nFriends = nFriends;
	}

	public int getnFans() {
		return nFans;
	}

	public void setnFans(int nFans) {
		this.nFans = nFans;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
