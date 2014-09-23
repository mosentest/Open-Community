package org.mu.cloudservice.service;

import java.util.List;

import org.mu.opencomm.common.entity.Permission;
import org.mu.opencomm.common.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("permissionService")
public class PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	public List<Permission> getPermissions(long user) {
		return permissionRepository.getPermissions(user);
	}

	public PermissionRepository getPermissionRepository() {
		return permissionRepository;
	}

	public void setPermissionRepository(PermissionRepository permissionRepository) {
		this.permissionRepository = permissionRepository;
	}
	
}
