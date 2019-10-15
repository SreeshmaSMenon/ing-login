package com.ing.loginregistration.service;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.ing.loginregistration.dto.LoginRequest;
import com.ing.loginregistration.entity.User;
import com.ing.loginregistration.enums.Role;
import com.ing.loginregistration.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {
	@Mock
	UserRepository userRepository;
	@InjectMocks
	LoginServiceImpl loginServiceImpl;
	User user;
	LoginRequest loginRequest;

	@Before
	public void setup() {
		user = new User();
		user.setUserId(1L);
		user.setEmail("sree@gmail.com");
		user.setUserName("sreeshma123");
		user.setPassword("$2a$10$rfLmJdcWl8qq9yYhOM9.Fufb3bHUDuL0vHeV2MH69PkAvNjtaF9jG");
		user.setRole(Role.USER);
		loginRequest = new LoginRequest();
		loginRequest.setUserName("sreeshma123");
		loginRequest.setPassword("sree123");
	}

	@Test
	public void testGetUerByUsernameAndPassword() {
		Mockito.when(userRepository.findByUserName(Mockito.any())).thenReturn(Optional.of(user));
		Optional<User> user = loginServiceImpl.getUerByUsernameAndPassword(loginRequest);
		assertNotNull(user);
	}
}
