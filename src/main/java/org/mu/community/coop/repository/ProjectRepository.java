package org.mu.community.coop.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mu.community.coop.entity.Project;
import org.springframework.stereotype.Repository;

@Repository("projectRepository")
public interface ProjectRepository {
	
	public void createProject(Project project);

	public List<Project> getUserProject(@Param("user") long user, @Param("mode") boolean mode, 
			@Param("offset") int offset, @Param("size") int size);
	
	public int countUserProject(@Param("user") long user, @Param("mode") boolean mode);
	
}
