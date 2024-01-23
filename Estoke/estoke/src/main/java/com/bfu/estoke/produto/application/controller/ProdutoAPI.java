package com.bfu.estoke.produto.application.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bfu.estoke.produto.domain.dto.request.ProdutoAtualizaRequest;
import com.bfu.estoke.produto.domain.dto.request.ProdutoRequest;
import com.bfu.estoke.produto.domain.dto.response.ProdutoDetalhadoResponse;
import com.bfu.estoke.produto.domain.dto.response.ProdutoListResponse;
import com.bfu.estoke.produto.domain.dto.response.ProdutoListResponseDetalhado;
import com.bfu.estoke.produto.domain.dto.response.ProdutoResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public interface ProdutoAPI {
	
	@PostMapping("/{idUser}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProdutoResponse post(@RequestBody @Valid ProdutoRequest request, @PathVariable UUID idUser);
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProdutoListResponseDetalhado> getAll();

	@GetMapping("/list/{idUser}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProdutoListResponse> getAllByIdUser(@PathVariable UUID idUser);

	@GetMapping("/{idProduto}")
	@ResponseStatus(code = HttpStatus.OK)
	public ProdutoDetalhadoResponse getProdutoById(@PathVariable UUID idProduto);
	
	@DeleteMapping("/{idProduto}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID idProduto);

	@PutMapping("/{idProduto}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void put(@RequestBody @Valid ProdutoAtualizaRequest request, @PathVariable UUID idProduto);
}