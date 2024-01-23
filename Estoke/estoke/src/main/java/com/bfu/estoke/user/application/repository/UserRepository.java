package com.bfu.estoke.user.application.repository;

import java.util.UUID;

import com.bfu.estoke.user.domain.User;


public interface UserRepository {
	User findByIdUser(UUID idUser);
	User findByEmail(String login);
	User salva(User user);
}
