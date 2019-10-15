package com.ing.loginregistration.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.ing.loginregistration.dto.UserRequest;
import com.ing.loginregistration.entity.User;
import com.ing.loginregistration.exception.EmailExistException;
import com.ing.loginregistration.exception.PasswordNotMatchExeption;
import com.ing.loginregistration.service.UserService;
import com.ing.loginregistration.util.IngLoginConstant;
import lombok.extern.slf4j.Slf4j;
/**
 * @since 2019-10-14
 *  This class will validate the userRequest properties.
 * @author Sreeshma S Menon
 */
@Slf4j
@Component
public class UserRequestValidator implements Validator {

	@Autowired
	UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UserRequest.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.debug(IngLoginConstant.VALIDATING);
		UserRequest bean = (UserRequest) target;
        validatePasswords(bean);
        validateEmail(bean);	
	}
	private void validatePasswords(UserRequest bean) {
        if (bean.getPassword()!=null&&!bean.getPassword().equals(bean.getPasswordRepeated())) {
            throw new PasswordNotMatchExeption(IngLoginConstant.NO_MATCH_VALE);
        }
    }

    private void validateEmail(UserRequest bean) {
    	Optional<User> user=userService.getUserByEmail(bean.getEmail());
    	if (user.isPresent()) {
            throw new EmailExistException(IngLoginConstant.EMAIL_EXIST_VALE);
        }
    }

}
