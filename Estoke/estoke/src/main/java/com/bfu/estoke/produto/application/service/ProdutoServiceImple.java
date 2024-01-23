package com.bfu.estoke.produto.application.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bfu.estoke.produto.application.repository.ProdutoRepository;
import com.bfu.estoke.produto.domain.Produto;
import com.bfu.estoke.produto.domain.dto.request.ProdutoAtualizaRequest;
import com.bfu.estoke.produto.domain.dto.request.ProdutoRequest;
import com.bfu.estoke.produto.domain.dto.response.ProdutoDetalhadoResponse;
import com.bfu.estoke.produto.domain.dto.response.ProdutoListResponse;
import com.bfu.estoke.produto.domain.dto.response.ProdutoListResponseDetalhado;
import com.bfu.estoke.produto.domain.dto.response.ProdutoResponse;
import com.bfu.estoke.user.application.repository.UserRepository;
import com.bfu.estoke.user.domain.User;
import com.bfu.estoke.user.infra.producers.UserProducer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImple implements ProdutoService {
	private final ProdutoRepository repository;
	private final UserRepository userRepository;
	private final UserProducer producer;

	@Override
	public ProdutoResponse cria(@Valid ProdutoRequest request, UUID idUser) {

		User user = userRepository.findByIdUser(idUser);
		Produto produto = repository.salva(new Produto(request, user));

		return ProdutoResponse.builder().idProduto(produto.getIdProduto()).build();
	}

	@Override
	public List<ProdutoListResponseDetalhado> getAll() {
		List<Produto> produtos = repository.getAll();
		return ProdutoListResponseDetalhado.converte(produtos);
	}

	@Override
	public List<ProdutoListResponse> getAllByIdUser(UUID idUser) {
		
		List<Produto> produtos = repository.getAllByIdUser(idUser);
		
		enviaEmailSeQuantidaBaixa(idUser, produtos);

		return ProdutoListResponse.converte(produtos);
	}

	@Override
	public ProdutoDetalhadoResponse getById(UUID idProduto) {
		Produto produto = repository.getById(idProduto);
		return new ProdutoDetalhadoResponse(produto);
	}

	@Override
	public void deleteProduto(UUID idProduto) {
		Produto produto = repository.getById(idProduto);
		repository.delete(produto);
	}

	@Override
	public void putProduto(UUID idProduto, @Valid ProdutoAtualizaRequest request) {
		Produto produto = repository.getById(idProduto);
		produto.atualiza(request);
		repository.salva(produto);
	}
	
	public void enviaEmailSeQuantidaBaixa(UUID idUser, List<Produto> produtos) {
		User user = userRepository.findByIdUser(idUser);
	   
		List<Produto> produtosBaixos = produtos.stream()
	            .filter(produto -> produto.getQuantity() < 5)
	            .collect(Collectors.toList());
	   
		if (!produtosBaixos.isEmpty()) {
	    	 producer.publishMessageEmail(user, produtosBaixos);
	    }
	}
}