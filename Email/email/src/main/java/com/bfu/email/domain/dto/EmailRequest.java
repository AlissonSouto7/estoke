package com.bfu.email.domain.dto;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
