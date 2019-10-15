package com.ing.loginregistration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ing.loginregistration.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 Optional<User> findByEmail(String email);
 Optional<User>findByUserName(String userName);
}
