package com.ing.loginregistration.service;

import java.util.List;
import java.util.Optional;

import com.ing.loginregistration.dto.UserDetail;
import com.ing.loginregistration.dto.UserRequest;
import com.ing.loginregistration.entity.User;

public interface UserService {
  public Optional<User> save(UserRequest userRequest);
  public Optional<User> getUserByEmail(String email);
  public Optional<List<UserDetail>> getAllUser();
}
