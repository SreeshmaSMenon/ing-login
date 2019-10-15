package com.ing.loginregistration.service;

import java.util.Optional;

import com.ing.loginregistration.dto.LoginRequest;
import com.ing.loginregistration.entity.User;

public interface LoginService {
  public Optional<User> getUerByUsernameAndPassword(LoginRequest loginRequest);
}
