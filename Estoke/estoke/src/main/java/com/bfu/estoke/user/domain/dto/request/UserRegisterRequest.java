package com.bfu.estoke.user.domain.dto.request;

import com.bfu.estoke.user.domain.enums.Sexo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegisterRequest {
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String senha;
	@NotBlank
	private String nome;
	@NotNull
	private Sexo sexo;
}
