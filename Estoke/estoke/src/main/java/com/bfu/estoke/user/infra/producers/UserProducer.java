package com.bfu.estoke.user.infra.producers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bfu.estoke.produto.domain.Produto;
import com.bfu.estoke.user.domain.User;
import com.bfu.estoke.user.domain.dto.request.EmailRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(User user, List<Produto> produtos) {
    	
        String texto = user.getNome() + ", o estoque dos produtos abaixo está abaixo do esperado:\n" +
                produtos.stream()
                        .map(Produto::getName)
                        .collect(Collectors.joining("\n"));
    	
        EmailRequest email = new EmailRequest();
        email.setReferenciaDoPropietario(user.getIdUser());
        email.setDestinatario(user.getEmail());
        email.setAssunto("Estoke");
        email.setTexto(texto);

        rabbitTemplate.convertAndSend("", routingKey, email);
    }
}
