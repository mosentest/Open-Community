package org.mu.community.common.repository;

import java.util.List;

import org.mu.community.common.entity.Permission;
import org.springframework.stereotype.Repository;

@Repository("permissionRepository")
public interface PermissionRepository {

	public List<Permission> getPermissions(long user);
	
}
