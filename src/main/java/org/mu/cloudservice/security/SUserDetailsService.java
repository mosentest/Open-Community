package org.mu.cloudservice.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mu.jmdb.core.MongoEntityManager;
import org.mu.opencomm.common.entity.Permission;
import org.mu.opencomm.common.entity.User;
import org.mu.opencomm.common.repository.PermissionRepository;
import org.mu.opencomm.common.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(MongoEntityManager.class);

	@Resource
	private UserRepository userRepository;
	
	@Resource
	private PermissionRepository permissionRepository;

	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		UserDetails userDetails = null;
		try {
			User user = userRepository.getUser(account);
			if (user == null) {
				throw new UsernameNotFoundException("User '" + account + "' doesn't exist");
			}
			userDetails = new Authentication(user, user.getAccount(),
					user.getPassword(), true, true, true, true,
					grantAuthorities(user));
		} catch (UsernameNotFoundException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
		}
		return userDetails;
	}

	private List<GrantedAuthority> grantAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<Permission> permissions = permissionRepository.getPermissions(user.getId());
		for (Permission permission : permissions) {
			authorities.add(new SimpleGrantedAuthority(permission.getPermission()));
		}
		return authorities;
	}
}
