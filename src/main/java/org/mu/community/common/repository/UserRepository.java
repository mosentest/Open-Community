package org.mu.community.common.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mu.community.common.entity.User;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository {
	
	public List<User> search(String query);

	public User getCompleteUser(@Param("user") long user, @Param("path") String path);
	
	public long getId(String path);
	
	public User getUser(String username);
	
	public User getUserById(long id);
	
	public User getUserByPath(String path);
	
	public int addFriend(@Param("user") long user, @Param("friend") long friend);
	
	public int removeFriend(@Param("user") long user, @Param("friend") long friend);
	
	public int countFriends(long user);
	
	public int countFans(long user);
	
	public List<User> getFriends(long user);
	
	public List<User> getFans(long user);
	
	public User getUserByEmail(String email);
	
	public User getUserByAccount(String account);
	
	public int register(User user);
	
}
