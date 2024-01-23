package com.bfu.estoke.user.infra;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.bfu.estoke.handler.exceptions.UserNotFoundException;
import com.bfu.estoke.user.application.repository.UserRepository;
import com.bfu.estoke.user.domain.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserInfraRepository implements UserRepository {
	
	private final UserJPARepository jpaRepository;
	
	@Override
	public User salva(User user) {
		User usuario = jpaRepository.save(user);
		return usuario;
	}

	@Override
	public User findByIdUser(UUID idUser) {
		User user = jpaRepository.findById(idUser)
				.orElseThrow(() -> new UserNotFoundException("Usuario Não encontrado com o id " + idUser));
		return user;
	}

	@Override
	public User findByEmail(String login) {
		User user = jpaRepository.findByEmail(login);
		return user;
	}
}