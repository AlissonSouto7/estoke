package com.bfu.estoke.produto.infra;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.bfu.estoke.handler.exceptions.ProdutoNotFoundException;
import com.bfu.estoke.produto.application.repository.ProdutoRepository;
import com.bfu.estoke.produto.domain.Produto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProdutoInfraRepository implements ProdutoRepository {

	private final ProdutoJPARepository jpaRepository;
	
	@Override
	public Produto salva(Produto produto) {
		Produto newProduto = jpaRepository.save(produto);
		return newProduto;
	}

	@Override
	public List<Produto> getAll() {
		List<Produto> produtos = jpaRepository.findAll();
	    if (produtos.isEmpty()) {
	        throw new ProdutoNotFoundException("Ainda não há itens adicionados (:");
	    }
		return produtos;
	}

	@Override
	public List<Produto> getAllByIdUser(UUID idUser) {
		List<Produto> produtos = jpaRepository.findAllByUser_IdUser(idUser);
	    if (produtos.isEmpty()) {
	        throw new ProdutoNotFoundException("Você ainda não adicionou nenhum produto (:");
	    }
		return produtos;
	}

	@Override
	public Produto getById(UUID idProduto) {
		Produto produto = jpaRepository.findById(idProduto)
				.orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado com id "+ idProduto));
		return produto;
	}

	@Override
	public void delete(Produto produto) {
		jpaRepository.delete(produto);
	}
}
