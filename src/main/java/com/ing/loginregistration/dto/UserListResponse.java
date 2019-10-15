package com.ing.loginregistration.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserListResponse {
  private Integer statusCode;
  private String statusMessage;
  private List<UserDetail> userList;
}
