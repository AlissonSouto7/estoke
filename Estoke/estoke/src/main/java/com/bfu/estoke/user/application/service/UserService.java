package com.bfu.estoke.user.application.service;


import com.bfu.estoke.user.domain.dto.request.UserLoginRequest;
import com.bfu.estoke.user.domain.dto.request.UserRegisterRequest;
import com.bfu.estoke.user.domain.dto.response.UserResponse;
import com.bfu.estoke.user.domain.dto.response.UserResponseLogin;

import jakarta.validation.Valid;

public interface UserService {

	UserResponse registra(@Valid UserRegisterRequest request);

	UserResponseLogin loga(@Valid UserLoginRequest request);

}
