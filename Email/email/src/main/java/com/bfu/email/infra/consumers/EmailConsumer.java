package com.bfu.email.infra.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bfu.email.application.repository.EmailRepository;
import com.bfu.email.domain.Email;
import com.bfu.email.domain.dto.EmailRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class EmailConsumer {
	private final EmailRepository emailRepository;
	
	@RabbitListener(queues = "${broker.queue.email.name}")
	public void listen(@Payload EmailRequest request) {
		try {
			log.info("[START] EmailConsumer - listen");
			Email email = new Email(request);
			emailRepository.salva(email);
			System.out.println("Email Status: " + email.getStatusDoEmail().toString());
			log.info("[FINISH] EmailConsumer - listen");
		} catch (Exception e) {
			log.info("[ERRO]", e);
		}
	}
}