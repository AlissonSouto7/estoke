package com.bfu.estoke.produto.application.service;

import java.util.List;
import java.util.UUID;

import com.bfu.estoke.produto.domain.dto.request.ProdutoAtualizaRequest;
import com.bfu.estoke.produto.domain.dto.request.ProdutoRequest;
import com.bfu.estoke.produto.domain.dto.response.ProdutoDetalhadoResponse;
import com.bfu.estoke.produto.domain.dto.response.ProdutoListResponse;
import com.bfu.estoke.produto.domain.dto.response.ProdutoListResponseDetalhado;
import com.bfu.estoke.produto.domain.dto.response.ProdutoResponse;

import jakarta.validation.Valid;

public interface ProdutoService {

	ProdutoResponse cria(@Valid ProdutoRequest request, UUID idUser);

	List<ProdutoListResponseDetalhado> getAll();

	List<ProdutoListResponse> getAllByIdUser(UUID idUser);

	ProdutoDetalhadoResponse getById(UUID idProduto);

	void deleteProduto(UUID idProduto);

	void putProduto(UUID idProduto, @Valid ProdutoAtualizaRequest request);

}
