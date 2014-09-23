package org.mu.cloudservice.service;

import java.util.Date;
import java.util.List;

import org.mu.cloudservice.constants.PropertiesManager;
import org.mu.cloudservice.dto.UserDTO;
import org.mu.cloudservice.entity.User;
import org.mu.cloudservice.exception.InfoException;
import org.mu.cloudservice.repository.UserRepository;
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
	
	public User getUser(long id) {
		return userRepository.getUserById(id);
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

    @Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
}
