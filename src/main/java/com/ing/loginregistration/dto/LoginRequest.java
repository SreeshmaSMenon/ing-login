package com.ing.loginregistration.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
	@NotEmpty
	private String userName;
	@NotEmpty
	private String password;
}
