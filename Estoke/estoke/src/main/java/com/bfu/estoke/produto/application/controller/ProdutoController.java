package com.bfu.estoke.produto.application.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.RestController;

import com.bfu.estoke.produto.application.service.ProdutoService;
import com.bfu.estoke.produto.domain.dto.request.ProdutoAtualizaRequest;
import com.bfu.estoke.produto.domain.dto.request.ProdutoRequest;
import com.bfu.estoke.produto.domain.dto.response.ProdutoDetalhadoResponse;
import com.bfu.estoke.produto.domain.dto.response.ProdutoListResponse;
import com.bfu.estoke.produto.domain.dto.response.ProdutoListResponseDetalhado;
import com.bfu.estoke.produto.domain.dto.response.ProdutoResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProdutoController implements ProdutoAPI {

	private final ProdutoService service;

	@Override
	public ProdutoResponse post(@Valid ProdutoRequest request, UUID idUser) {
		ProdutoResponse produto = service.cria(request,idUser);
		return produto;
	}

	@Override
	public List<ProdutoListResponseDetalhado> getAll() {
		List<ProdutoListResponseDetalhado> listaProdutos = service.getAll();
		return listaProdutos;
	}

	@Override
	public List<ProdutoListResponse> getAllByIdUser(UUID idUser) {
		List<ProdutoListResponse> listaProdutosUser = service.getAllByIdUser(idUser);
		return listaProdutosUser;
	}

	@Override
	public ProdutoDetalhadoResponse getProdutoById(UUID idProduto) {
		ProdutoDetalhadoResponse produto = service.getById(idProduto);
		return produto;
	}

	@Override
	public void delete(UUID idProduto) {
		service.deleteProduto(idProduto);
	}

	@Override
	public void put(@Valid ProdutoAtualizaRequest request, UUID idProduto) {
		service.putProduto(idProduto, request);
	}
}