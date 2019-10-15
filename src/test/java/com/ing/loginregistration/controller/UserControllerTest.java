package com.ing.loginregistration.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ing.loginregistration.dto.UserDetail;
import com.ing.loginregistration.dto.UserListResponse;
import com.ing.loginregistration.dto.UserRequest;
import com.ing.loginregistration.dto.UserSaveResponse;
import com.ing.loginregistration.entity.User;
import com.ing.loginregistration.enums.Role;
import com.ing.loginregistration.exception.BindException;
import com.ing.loginregistration.service.UserService;
import com.ing.loginregistration.validator.UserRequestValidator;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@Mock
	UserService userService;
    @Mock
	UserRequestValidator userRequestValidator;
    @InjectMocks
    UserController userController;  
    BindingResult bindingResult;
    User user;
    FieldError fieldError;
    UserRequest userRequest;
    List<UserDetail>userList;
    
    @Before
    public void setup() {
 	   bindingResult = mock(BindingResult.class);
 	   user=new User();
 	   user.setUserId(1L);
 	   user.setEmail("sree@gmail.com");
 	   user.setUserName("sreeshma123");
 	   user.setPassword("sree123");
 	   user.setRole(Role.USER);
 	   userRequest=new UserRequest();
 	   BeanUtils.copyProperties(user, userRequest);
 	   userRequest.setPasswordRepeated("sree123");
 	   fieldError=new FieldError("loginRequest", "userName", "Must Not empty");
 	   userList=new ArrayList<>();
 	   UserDetail userDetail=new UserDetail("sreeshma123", "sree@gmail.com", 1L);
 	   userList.add(userDetail);
    }
    
    @Test
    public void testSave() {
 	   Mockito.when(bindingResult.hasErrors()).thenReturn(false);
 	   Optional<User> optionalUser=Optional.of(user);
	   Mockito.when( userService.save(Mockito.any())).thenReturn(optionalUser);
       ResponseEntity<UserSaveResponse> userSaveResponse=userController.save(userRequest, bindingResult);
       assertNotNull(userSaveResponse);
    }
    @Test(expected=BindException.class)
    public void testSaveBindException() {
 	   Mockito.when(bindingResult.hasErrors()).thenReturn(true);
	   Mockito.when(bindingResult.getFieldError()).thenReturn(fieldError);
 	   ResponseEntity<UserSaveResponse> userSaveResponse=userController.save(userRequest, bindingResult);
       assertNotNull(userSaveResponse);
    }
    
    @Test
    public void testGetallUser() {
    	Optional<List<UserDetail>> optionalUserList=Optional.of(userList);
  	    Mockito.when(userService.getAllUser()).thenReturn(optionalUserList);
        ResponseEntity<UserListResponse> userListResponse=userController.getAllUser();
        assertNotNull(userListResponse);
    }
}
