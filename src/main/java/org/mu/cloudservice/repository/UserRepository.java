package org.mu.cloudservice.repository;

import org.apache.ibatis.annotations.Param;
import org.mu.cloudservice.entity.User;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository {

	public User getCompleteUser(@Param("user") long user, @Param("path") String path);
	
	public User getUser(String username);
	
	public User getUserById(long id);
	
	public User getUserByEmail(String email);
	
	public User getUserByAccount(String account);
	
	public int register(User user);
	
}
