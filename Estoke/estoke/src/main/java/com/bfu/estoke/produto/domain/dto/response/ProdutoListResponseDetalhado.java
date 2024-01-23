package com.bfu.estoke.produto.domain.dto.response;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.bfu.estoke.produto.domain.Produto;

import lombok.Data;

@Data
public class ProdutoListResponseDetalhado {
	private UUID idProduto;
	private String name;
	private Integer quantity;

	public static List<ProdutoListResponseDetalhado> converte(List<Produto> produtos) {
		return produtos.stream().map(ProdutoListResponseDetalhado::new).collect(Collectors.toList());
	}

	public ProdutoListResponseDetalhado(Produto produto) {
		this.idProduto = produto.getIdProduto();
		this.name = produto.getName();
		this.quantity = produto.getQuantity();
	}
}
