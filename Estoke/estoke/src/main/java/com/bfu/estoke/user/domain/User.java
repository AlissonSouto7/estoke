package com.bfu.estoke.user.domain;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bfu.estoke.produto.domain.Produto;
import com.bfu.estoke.user.domain.dto.request.UserRegisterRequest;
import com.bfu.estoke.user.domain.enums.Roles;
import com.bfu.estoke.user.domain.enums.Sexo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User implements UserDetails{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idUser;

	@Column(unique = true)
	private String email;
	
	private String senha;

	@Enumerated(EnumType.STRING)
	private Roles roles;
	
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@OneToMany(mappedBy = "user")
	private List<Produto> produtos;

	public User(@Valid UserRegisterRequest request) {
		this.email = request.getEmail();
		this.senha = new BCryptPasswordEncoder().encode(request.getSenha());
		this.roles = Roles.USER;
		this.nome = request.getNome();
		this.sexo = request.getSexo();
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.roles.equals(Roles.ADMIN)) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}