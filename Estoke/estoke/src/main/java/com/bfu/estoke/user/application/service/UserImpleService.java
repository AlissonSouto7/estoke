package com.bfu.estoke.user.application.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.bfu.estoke.handler.exceptions.EmailNotFoundException;
import com.bfu.estoke.handler.exceptions.ExistingEmailException;
import com.bfu.estoke.handler.exceptions.InvalidCredentialsException;
import com.bfu.estoke.security.TokenService;
import com.bfu.estoke.user.application.repository.UserRepository;
import com.bfu.estoke.user.domain.User;
import com.bfu.estoke.user.domain.dto.request.UserLoginRequest;
import com.bfu.estoke.user.domain.dto.request.UserRegisterRequest;
import com.bfu.estoke.user.domain.dto.response.UserResponse;
import com.bfu.estoke.user.domain.dto.response.UserResponseLogin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserImpleService implements UserService {
	
	private final UserRepository repository;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	
	@Override
	public UserResponse registra(@Valid UserRegisterRequest request) {
		
		verificaEmailExistente(request.getEmail());
		
		User user = repository.salva(new User(request));
		
		String token = tokenService.generateToken(user);
		
		return UserResponse.builder().idUser(user.getIdUser()).token(token).build();
	}

	@Override
	public UserResponseLogin loga(@Valid UserLoginRequest request) {
    	
		String token = verificaCredenciaisEGeraToken(request.getEmail(), request.getSenha());
		
		return UserResponseLogin.builder().token(token).build();
	}
	
	private void verificaEmailExistente(String email) {
	    
	    if (repository.findByEmail(email) != null) {
	        throw new ExistingEmailException("Esse Email já foi cadastrado com " + email);
	    }
	}
	
	private String verificaCredenciaisEGeraToken(String email, String senha) {
		try {
			var credentials = new UsernamePasswordAuthenticationToken(email, senha);
		       
	    	var authentication = authenticationManager.authenticate(credentials);
			
			String token = tokenService.generateToken((User) authentication.getPrincipal());
			
			return token;
		} catch (BadCredentialsException e) {
			throw new InvalidCredentialsException("Senha incorreta");
		} catch (InternalAuthenticationServiceException e) {
	        throw new EmailNotFoundException("Email não encontrado com " + email);
	    }
	}
}