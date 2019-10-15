package com.ing.loginregistration.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {
	@NotEmpty
    private String userName;
	@NotEmpty
	@Email
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String passwordRepeated;

}