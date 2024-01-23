package com.bfu.estoke.produto.domain.dto.response;

import java.util.UUID;

import com.bfu.estoke.produto.domain.Produto;

import lombok.Data;

@Data
public class ProdutoDetalhadoResponse {
	
	private UUID idUser;
	private String name;
	private String color;
	private Integer quantity;
	private Double price;
	private Double total;
	
	public ProdutoDetalhadoResponse(Produto produto) {
		this.idUser = produto.getUser().getIdUser();
		this.name = produto.getName();
		this.color = produto.getColor();
		this.quantity = produto.getQuantity();
		this.price = produto.getPrice();
		this.total = produto.getTotal();
	}
}
