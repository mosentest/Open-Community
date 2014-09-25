package org.mu.community.common.entity;

import java.io.Serializable;

public class Permission implements Serializable {

	private static final long serialVersionUID = -4367278630921611865L;

	private int id;
	
	private String permission;
	
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
