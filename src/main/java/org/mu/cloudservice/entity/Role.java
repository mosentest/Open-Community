package org.mu.cloudservice.entity;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {

	private static final long serialVersionUID = -5968525626641553496L;
	
	private int id;
	
	private String name;
	
	private String description;
	
	private List<Permission> permissions;

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
