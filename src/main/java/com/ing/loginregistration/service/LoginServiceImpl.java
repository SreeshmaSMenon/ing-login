package com.ing.loginregistration.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.ing.loginregistration.dto.LoginRequest;
import com.ing.loginregistration.entity.User;
import com.ing.loginregistration.exception.UserNotFoundException;
import com.ing.loginregistration.repository.UserRepository;
import com.ing.loginregistration.util.IngLoginConstant;

import lombok.extern.slf4j.Slf4j;

/**
 * @since 2019-10-14 This class includes methods login to the application.
 * @author Sreeshma S Menon
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	UserRepository userRepository;

	/**
	 * @param loginRequest
	 * @return optional of User This method will accept loginRequest as input and
	 *         get user object for given user name and password.Then return optional
	 *         of user.
	 */
	@Override
	public Optional<User> getUerByUsernameAndPassword(LoginRequest loginRequest) {
		log.debug(IngLoginConstant.LOGIN_DEBUG_START_SERVICE);
		Optional<User> optionalUser = userRepository.findByUserName(loginRequest.getUserName());
		if(!(optionalUser.isPresent() && BCrypt.checkpw(loginRequest.getPassword(), optionalUser.get().getPassword()))) {
			throw new UserNotFoundException(IngLoginConstant.USER_NOT_FOUND);
		}
		log.debug(IngLoginConstant.LOGIN_DEBUG_END_SERVICE);
		return optionalUser;
	}

}
