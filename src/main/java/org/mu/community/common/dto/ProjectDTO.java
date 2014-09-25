package org.mu.community.common.dto;

import org.mu.community.coop.entity.Project;

public class ProjectDTO implements DataTransferObject<Project> {
	
	private String name;
	
	private String description;
	
	private String repositoryUrl;
	
	private boolean mode;
	
	public ProjectDTO() {}

	@Override
	public Project toObject() {
		Project project = new Project();
		project.setName(name);
		project.setDescription(description);
		project.setRepositoryUrl(repositoryUrl);
		project.setMode(mode);
		return project;
	}

	@Override
	public ProjectDTO toDTO(Project object) {
		return null;
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

	public boolean isMode() {
		return mode;
	}

	public void setMode(boolean mode) {
		this.mode = mode;
	}
}
