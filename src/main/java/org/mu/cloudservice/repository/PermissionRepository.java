package org.mu.cloudservice.repository;

import java.util.List;

import org.mu.opencomm.common.entity.Permission;
import org.springframework.stereotype.Repository;

@Repository("permissionRepository")
public interface PermissionRepository {

	public List<Permission> getPermissions(long user);
	
}
