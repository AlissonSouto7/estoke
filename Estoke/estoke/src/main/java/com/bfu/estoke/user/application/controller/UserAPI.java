package com.bfu.estoke.user.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bfu.estoke.user.domain.dto.request.UserLoginRequest;
import com.bfu.estoke.user.domain.dto.request.UserRegisterRequest;
import com.bfu.estoke.user.domain.dto.response.UserResponse;
import com.bfu.estoke.user.domain.dto.response.UserResponseLogin;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public interface UserAPI {
	
	@PostMapping("/register")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserResponse register(@RequestBody @Valid UserRegisterRequest request);

	@PostMapping("/login")
	@ResponseStatus(code = HttpStatus.CREATED)
	public UserResponseLogin login(@RequestBody @Valid UserLoginRequest request);
}