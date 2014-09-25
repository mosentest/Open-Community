package org.mu.community.common.service;

import java.util.Date;
import java.util.List;

import org.mu.community.common.constants.PropertiesManager;
import org.mu.community.common.dto.UserDTO;
import org.mu.community.common.entity.User;
import org.mu.community.common.exception.InfoException;
import org.mu.community.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserService {

	private UserRepository userRepository;

	private MessageDigestPasswordEncoder passwordEncoder;
	
	private final String SALT = PropertiesManager.getProperty("md5.salt", "/%El-B9ua* vbo@N#,WU[+Mp+c-5#zgP&1w^-I*#|r]i`HdQ7eMTA$UCFXnA]2xR");
	
	public UserService() {
		passwordEncoder = new Md5PasswordEncoder();
	}
	
	@Transactional(readOnly = true)
	public User getCompleteUser(long user, String path, long auth) throws Exception {
		User object = userRepository.getCompleteUser(user, path);
		if (object == null) {
			throw new InfoException("User doesn't exist");
		}
		return object;
	}
	
	public User getUser(long id) {
		return userRepository.getUserById(id);
	}
	
	public long getId(String path) {
		return userRepository.getId(path);
	}
	
	public User getUser(String account) {
		return userRepository.getUserByAccount(account);
	}
	
	@Transactional
	public void register(UserDTO dto) throws InfoException {
		User user = dto.toObject();
		if (userRepository.getUserByAccount(user.getAccount()) != null) {
			throw new InfoException("Account is not available.");
		}
		if (userRepository.getUserByEmail(user.getEmail()) != null) {
			throw new InfoException("Email has already been registered.");
		}
		user.setPath(user.getPath().toLowerCase());
		if (userRepository.getUserByPath(user.getPath()) != null) {
			throw new InfoException("Path is not available.");
		}
		user.setCreate(new Date());
		user.setUpdate(new Date());
		user.setVerified(false);
		user.setProfile("default.jpg");
		user.setPassword(passwordEncoder.encodePassword(user.getPassword(), SALT));
		int result = userRepository.register(user);
		if (result != 1) {
			throw new InfoException("Unable to register.");
		}
	}
	
	public List<User> search(String query) {
		return userRepository.search("%" + query + "%");
	}
	
	public void addFriend(long user, long friend) throws InfoException {
		try {
			userRepository.addFriend(user, friend);
		} catch (Exception e) {
			throw new InfoException("Could not add user.");
		}
	}
	
	public void removeFriend(long user, long friend) throws InfoException {
		userRepository.removeFriend(user, friend);
	}

	public List<User> getFriends(long user) {
		return userRepository.getFriends(user);
	}

	public List<User> getFans(long user) {
		return userRepository.getFans(user);
	}
	
	public int countFriends(long user) {
		return userRepository.countFriends(user);
	}
	
	public int countFans(long user) {
		return userRepository.countFans(user);
	}

    @Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
}
