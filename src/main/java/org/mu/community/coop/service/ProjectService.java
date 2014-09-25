package org.mu.community.coop.service;

import java.util.Date;
import java.util.List;

import org.mu.community.common.dto.ProjectDTO;
import org.mu.community.common.entity.User;
import org.mu.community.common.exception.InfoException;
import org.mu.community.coop.entity.Project;
import org.mu.community.coop.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("projectService")
public class ProjectService {

	private ProjectRepository projectRepository;
	
	@Transactional
	public long createProject(ProjectDTO dto, long user) throws InfoException {
		Project project = dto.toObject();
		project.setManager(new User(user));
		project.setUpdate(new Date());
		project.setCreate(new Date());
		project.setImagePath("default.png");
		projectRepository.createProject(project);
		if (project.getId() == 0) {
			throw new InfoException("Could not create project.");
		}
		return project.getId();
	}
	
	public List<Project> getUserProject(long user, boolean mode, int page, int size) {
		return projectRepository.getUserProject(user, mode, page * size, size);
	}
	
	public int countUserProject(long user, boolean mode) {
		return projectRepository.countUserProject(user, mode);
	}

    @Autowired
	public void setProjectRepository(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
}
