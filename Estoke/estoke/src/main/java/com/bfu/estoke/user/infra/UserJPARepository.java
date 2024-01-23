package com.bfu.estoke.user.infra;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.bfu.estoke.user.domain.User;

public interface UserJPARepository extends JpaRepository<User, UUID>{
	User findByEmail(String email);
	UserDetails getByEmail(String email);
}
