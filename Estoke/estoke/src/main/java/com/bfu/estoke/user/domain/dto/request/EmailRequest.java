package com.bfu.estoke.user.domain.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmailRequest {
	@NotNull
	private UUID referenciaDoPropietario;
	
	@NotBlank
	@Email
	private String remetente;
	
	@NotBlank
	@Email
	private String destinatario;
	
	@NotBlank
	private String assunto;
	
	@NotBlank
	private String texto;
}