package com.bfu.estoke.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bfu.estoke.user.domain.User;
import com.bfu.estoke.user.infra.UserJPARepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImple implements UserDetailsService {
	
	private final UserJPARepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = (User) repository.getByEmail(username);
		return user;
	}
}
