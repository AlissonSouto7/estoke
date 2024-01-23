package com.bfu.email.infra;

import java.time.LocalDateTime;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import com.bfu.email.application.repository.EmailRepository;
import com.bfu.email.domain.Email;
import com.bfu.email.domain.StatusEmail;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@RequiredArgsConstructor
@Log4j2
public class EmailInfraRepository implements EmailRepository {
	
	private final EmailJpaRepository repository;
	private final JavaMailSender emailSender;
	
	@Override
	public Email salva(Email email) {
		log.info("[START] EmailInfraRepository - salva");
		email.setDataDoEmail(LocalDateTime.now());
		enviarEmail(email);
		log.info("[FINISH] EmailInfraRepository - salva");
		return email;
	}
	
	private void enviarEmail (Email email) {
		log.info("[START] EmailInfraRepository - enviarEmail");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(email.getRemetente());
		message.setTo(email.getDestinatario());
		message.setSubject(email.getAssunto());
		message.setText(email.getTexto());
		log.info("[FINISH] EmailInfraRepository - enviarEmail");
		
		try {
			emailSender.send(message);
			email.setStatusDoEmail(StatusEmail.ENVIADO);
			
		} catch (MailException e) {
			System.out.println(e);
			email.setStatusDoEmail(StatusEmail.ERRO);
			
		} finally {
			repository.save(email);
		}
	}
}