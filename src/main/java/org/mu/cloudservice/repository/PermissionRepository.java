package org.mu.cloudservice.repository;

import org.mu.cloudservice.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("permissionRepository")
public interface PermissionRepository {

	public List<Permission> getPermissions(long user);
	
}
