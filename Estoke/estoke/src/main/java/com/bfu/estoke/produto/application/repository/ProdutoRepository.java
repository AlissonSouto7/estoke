package com.bfu.estoke.produto.application.repository;

import java.util.List;
import java.util.UUID;

import com.bfu.estoke.produto.domain.Produto;

public interface ProdutoRepository {
	Produto salva(Produto produto);

	List<Produto> getAll();

	List<Produto> getAllByIdUser(UUID idUser);

	Produto getById(UUID idProduto);

	void delete(Produto produto);
}
