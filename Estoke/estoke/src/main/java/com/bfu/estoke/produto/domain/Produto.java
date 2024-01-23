package com.bfu.estoke.produto.domain;

import java.util.UUID;

import com.bfu.estoke.produto.domain.dto.request.ProdutoAtualizaRequest;
import com.bfu.estoke.produto.domain.dto.request.ProdutoRequest;
import com.bfu.estoke.user.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idProduto;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private String name;
	
	private String color;
	
	private Integer quantity;
	
	private Double price;
	
	@Positive
	@NotNull
	private Double total;
	
	public Produto(@Valid ProdutoRequest request, User user) {
		this.user = user;
		this.name = request.getName();
		this.color = request.getColor();
		this.quantity = request.getQuantity();
		this.price = request.getPrice();
		this.total = this.price * this.quantity;
	}

	public void atualiza(@Valid ProdutoAtualizaRequest request) {
		this.name = request.getName();
		this.color = request.getColor();
		this.quantity = request.getQuantity();
		this.price = request.getPrice();
		this.total = this.price * this.quantity;
	}
}