package com.ing.loginregistration.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.ing.loginregistration.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user")
@Setter
@Getter
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, updatable = false)
	Long userId;
	@Column(name = "user_name", nullable = false)
	String userName;
	@Column(name = "email", nullable = false, unique = true)
	String email;
	@Column(name = "password", nullable = false)
	String password;
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	Role role;
}
