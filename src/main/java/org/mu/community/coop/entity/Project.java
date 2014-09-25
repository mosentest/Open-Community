package org.mu.community.coop.entity;

import java.util.Date;

import org.mu.community.common.entity.User;

public class Project {

	private long id;
	
	private User manager;
	
	private Date create;
	
	private Date update;
	
	private boolean mode;
	
	private String name;
	
	private String description;
	
	private String imagePath;
	
	private String repositoryUrl;
	
	private int nFork;
	
	private int nWatching;
	
	public Project() {
		
	}
	
	public Project(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
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

	public boolean isMode() {
		return mode;
	}

	public void setMode(boolean mode) {
		this.mode = mode;
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

	public String getRepositoryUrl() {
		return repositoryUrl;
	}

	public void setRepositoryUrl(String repositoryUrl) {
		this.repositoryUrl = repositoryUrl;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getnFork() {
		return nFork;
	}

	public void setnFork(int nFork) {
		this.nFork = nFork;
	}

	public int getnWatching() {
		return nWatching;
	}

	public void setnWatching(int nWatching) {
		this.nWatching = nWatching;
	}
	
}
