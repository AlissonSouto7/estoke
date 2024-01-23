package com.bfu.estoke.user.application.controller;

import org.springframework.web.bind.annotation.RestController;

import com.bfu.estoke.user.application.service.UserService;
import com.bfu.estoke.user.domain.dto.request.UserLoginRequest;
import com.bfu.estoke.user.domain.dto.request.UserRegisterRequest;
import com.bfu.estoke.user.domain.dto.response.UserResponse;
import com.bfu.estoke.user.domain.dto.response.UserResponseLogin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController implements UserAPI {
	
	private final UserService service;
	
	@Override
	public UserResponse register(@Valid UserRegisterRequest request) {
		UserResponse user = service.registra(request);
		return user;
	}

	@Override
	public UserResponseLogin login(@Valid UserLoginRequest request) {
		UserResponseLogin user = service.loga(request);
		return user;
	}
}