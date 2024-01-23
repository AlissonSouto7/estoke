package com.bfu.email.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.bfu.email.domain.dto.EmailRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Email {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID emailId;
	
	private UUID referenciaDoPropietario;

	private String remetente;
	
	private String destinatario;
	
	private String assunto;
	
	@Column(columnDefinition = "TEXT")
	private String texto;
	
	private LocalDateTime dataDoEmail;
	
	@Enumerated(EnumType.STRING)
	private StatusEmail statusDoEmail;
	
	public Email(@Valid EmailRequest request) {
		this.referenciaDoPropietario = request.getReferenciaDoPropietario();
		this.remetente = request.getRemetente();
		this.destinatario = request.getDestinatario();
		this.assunto = request.getAssunto();
		this.texto = request.getTexto();
	}
}