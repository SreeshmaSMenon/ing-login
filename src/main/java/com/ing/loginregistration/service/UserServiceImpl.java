package com.ing.loginregistration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ing.loginregistration.dto.UserDetail;
import com.ing.loginregistration.dto.UserRequest;
import com.ing.loginregistration.entity.User;
import com.ing.loginregistration.enums.Role;
import com.ing.loginregistration.repository.UserRepository;
import com.ing.loginregistration.util.IngLoginConstant;

import lombok.extern.slf4j.Slf4j;

/**
 * @since 2019-10-14 This class includes methods for register and retrieve user
 * @author Sreeshma S Menon
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * @param userRequest
	 * @return String which includes the message that user created successfully or
	 *         not. This method will accept userRequest  as input
	 *         and call the save method in repository and return back success
	 *         message if successfully registered else throw respective exception.
	 */
	@Override
	public Optional<User> save(UserRequest userRequest) {
		log.debug(IngLoginConstant.CREATE_USER_DEBUG_START_SERVICE);
		User user = new User();
		BeanUtils.copyProperties(userRequest, user);
		user.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
		user.setRole(Role.USER);
		user = userRepository.save(user);
		log.debug(IngLoginConstant.CREATE_USER_DEBUG_START_SERVICE);
		return Optional.of(user);
	}

	/**
	 * @param email
	 * @return Optional of User
	 * This method will accept email and return respective user object.
	 */
	@Override
	public Optional<User> getUserByEmail(String email) {
		log.debug(IngLoginConstant.GET_USER_BY_EMAIL_DEBUG_START_SERVICE);
         Optional<User> userOptional= userRepository.findByEmail(email);
		log.debug(IngLoginConstant.GET_USER_BY_EMAIL_DEBUG_END_SERVICE);
		return userOptional;
	}
	
	/**
	 * @return Optional of List<UserDetail> 
	 *       This method retrieve all user details.
	 */
	@Override
	public Optional<List<UserDetail>> getAllUser() {
		log.debug(IngLoginConstant.GET_ALL_USERS_DEBUG_START_SERVICE);
		List<UserDetail> userDetails=new ArrayList<>();
		List<User> userlist=userRepository.findAll();
		userlist.forEach(user->userDetails.add(new UserDetail(user.getUserName(), user.getEmail(), user.getUserId())));
		log.debug(IngLoginConstant.GET_ALL_USERS_DEBUG_END_SERVICE);
		return Optional.of(userDetails);
	}

}
