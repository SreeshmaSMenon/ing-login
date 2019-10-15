package com.ing.loginregistration.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import com.ing.loginregistration.dto.LoginRequest;
import com.ing.loginregistration.dto.LoginResponse;
import com.ing.loginregistration.entity.User;
import com.ing.loginregistration.enums.Role;
import com.ing.loginregistration.exception.BindException;
import com.ing.loginregistration.service.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class LonginControllerTest {

   @Mock
   LoginService loginService;  
   @InjectMocks
   LoginController loginController;   
   BindingResult bindingResult;
   User user;
   LoginRequest loginRequest;
   FieldError fieldError;
   
   @Before
   public void setup() {
	   bindingResult = mock(BindingResult.class);
	   user=new User();
	   user.setUserId(1L);
	   user.setEmail("sree@gmail.com");
	   user.setUserName("sreeshma123");
	   user.setPassword("sredsdsf");
	   user.setRole(Role.USER);
	   loginRequest=new LoginRequest();
	   loginRequest.setUserName("sreeshma123");
	   loginRequest.setPassword("sredsdsf");
	   fieldError=new FieldError("loginRequest", "userName", "Must Not empty");
   }
   
   @Test
   public void testLogin() {
	   Mockito.when(bindingResult.hasErrors()).thenReturn(false);
	   Mockito.when(loginService.getUerByUsernameAndPassword(Mockito.any())).thenReturn(Optional.of(user));
	   ResponseEntity<LoginResponse> loginResponse=loginController.login(loginRequest, bindingResult);
	   assertNotNull(loginResponse);
   }
   @Test(expected=BindException.class)
   public void testLoginBindException() {
	   Mockito.when(bindingResult.hasErrors()).thenReturn(true);
	   Mockito.when(bindingResult.getFieldError()).thenReturn(fieldError);
	   ResponseEntity<LoginResponse> loginResponse=loginController.login(loginRequest, bindingResult);
	   assertNotNull(loginResponse);
   }

}
