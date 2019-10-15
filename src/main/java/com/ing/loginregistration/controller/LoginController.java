package com.ing.loginregistration.controller;



import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ing.loginregistration.dto.LoginRequest;
import com.ing.loginregistration.dto.LoginResponse;
import com.ing.loginregistration.entity.User;
import com.ing.loginregistration.exception.BindException;
import com.ing.loginregistration.exception.UserNotFoundException;
import com.ing.loginregistration.service.LoginService;
import com.ing.loginregistration.util.IngLoginConstant;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@CrossOrigin(allowedHeaders = {"*","*/"}, origins = {"*","*/"})
public class LoginController {
	@Autowired
	LoginService loginService;
	/**
	 * @param loginRequest 
	 * @param bindingResult
	 * @return ResponseEntity of LoginResponse 
	 */
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
    	 log.debug(IngLoginConstant.CREATE_USER_DEBUG_START_CONTROLLER);
    	 LoginResponse loginResponse=new LoginResponse();
        if (bindingResult.hasErrors()) {
        	throw new BindException(bindingResult.getFieldError().getField()+" "+bindingResult.getFieldError().getDefaultMessage());
        }
        Optional<User> optionalUser=loginService.getUerByUsernameAndPassword(loginRequest);
        if(optionalUser.isPresent()) {
        	loginResponse.setStatusCode(HttpStatus.OK.value());
        	loginResponse.setStatusMessage(IngLoginConstant.SUCCESS);
        	loginResponse.setUserId(optionalUser.get().getUserId());
        }else {
        	throw new UserNotFoundException(IngLoginConstant.USER_NOT_FOUND);
        }
   	    log.debug(IngLoginConstant.CREATE_USER_DEBUG_END_CONTROLLER);
        return new ResponseEntity<>(loginResponse,HttpStatus.CREATED);
    }
	
}
