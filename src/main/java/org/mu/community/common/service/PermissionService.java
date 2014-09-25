package org.mu.community.common.service;

import java.util.List;

import org.mu.community.common.entity.Permission;
import org.mu.community.common.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("permissionService")
public class PermissionService {

	private PermissionRepository permissionRepository;
	
	public List<Permission> getPermissions(long user) {
		return permissionRepository.getPermissions(user);
	}

    @Autowired
	public void setPermissionRepository(PermissionRepository permissionRepository) {
		this.permissionRepository = permissionRepository;
	}
	
}
