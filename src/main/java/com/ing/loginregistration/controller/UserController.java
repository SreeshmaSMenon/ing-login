package com.ing.loginregistration.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ing.loginregistration.dto.UserDetail;
import com.ing.loginregistration.dto.UserListResponse;
import com.ing.loginregistration.dto.UserRequest;
import com.ing.loginregistration.dto.UserSaveResponse;
import com.ing.loginregistration.entity.User;
import com.ing.loginregistration.exception.BindException;
import com.ing.loginregistration.service.UserService;
import com.ing.loginregistration.util.IngLoginConstant;
import com.ing.loginregistration.validator.UserRequestValidator;

import lombok.extern.slf4j.Slf4j;

/**
 * @since 2019-10-14 This class includes methods for register and retrieve user
 * @author Sreeshma S Menon
 */
@Slf4j
@RestController
@RequestMapping("/users")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserRequestValidator userRequestValidator;

	@InitBinder("userRequest")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(userRequestValidator);
	}

	/**
	 * @param userRequest
	 * @param bindingResult
	 * @return ResponseEntity of String which includes the message that user created
	 *         successfully or not. This method will accept userRequest and
	 *         bindingResult as inputs and call the save method in service layer if
	 *         there is no binding errors, otherwise throw an exception.
	 */
	@PostMapping(value = "/")
	public ResponseEntity<UserSaveResponse> save(@Valid @RequestBody UserRequest userRequest,
			BindingResult bindingResult) {
		log.debug(IngLoginConstant.CREATE_USER_DEBUG_START_CONTROLLER);
		UserSaveResponse userSaveResponse = new UserSaveResponse();
		if (bindingResult.hasErrors()) {
			throw new BindException(
					bindingResult.getFieldError().getField() + " " + bindingResult.getFieldError().getDefaultMessage());
		}
		Optional<User> optionalUser = userService.save(userRequest);
		if (optionalUser.isPresent() && optionalUser.get().getUserId() != null) {
			userSaveResponse.setStatusCode(HttpStatus.OK.value());
			userSaveResponse.setMessage(IngLoginConstant.CREATE_SUCESS_MESSAGE);
		}
		log.debug(IngLoginConstant.CREATE_USER_DEBUG_END_CONTROLLER);
		return new ResponseEntity<>(userSaveResponse, HttpStatus.CREATED);
	}

	/**
	 * @return ResponseEntity of UserListResponse This method retrieve all user
	 *         details
	 */
	@GetMapping(value = "/")
	public ResponseEntity<UserListResponse> getAllUser() {
		log.debug(IngLoginConstant.GET_ALL_USERS_DEBUG_START_CONTROLLER);
		UserListResponse userListResponse = new UserListResponse();
		Optional<List<UserDetail>> optionalUserList = userService.getAllUser();
		if (optionalUserList.isPresent()) {
			userListResponse.setStatusCode(HttpStatus.OK.value());
			userListResponse.setStatusMessage(IngLoginConstant.SUCCESS);
			userListResponse.setUserList(optionalUserList.get());
		}
		log.debug(IngLoginConstant.GET_ALL_USERS_DEBUG_END_CONTROLLER);
		return new ResponseEntity<>(userListResponse, HttpStatus.CREATED);
	}

}
