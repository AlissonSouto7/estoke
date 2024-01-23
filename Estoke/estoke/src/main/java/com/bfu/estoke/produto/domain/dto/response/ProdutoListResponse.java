package com.bfu.estoke.produto.domain.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.bfu.estoke.produto.domain.Produto;

import lombok.Data;

@Data
public class ProdutoListResponse {
	private String name;
	private String color;
	private Integer quantity;
	private Double price;
	private Double total;

	public static List<ProdutoListResponse> converte(List<Produto> produtos) {
		return produtos.stream().map(ProdutoListResponse::new).collect(Collectors.toList());
	}

	public ProdutoListResponse(Produto produto) {
		this.name = produto.getName();
		this.color = produto.getColor();
		this.quantity = produto.getQuantity();
		this.price = produto.getPrice();
		this.total = produto.getTotal();
	}
}