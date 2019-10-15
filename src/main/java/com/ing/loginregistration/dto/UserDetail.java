package com.ing.loginregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDetail {
  private String userName;
  private String email;
  private Long userId; 
}
