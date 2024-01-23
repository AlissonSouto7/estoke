package com.bfu.estoke.produto.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProdutoRequest {
	@NotBlank
	private String name;
	
	@NotBlank
	private String color;
	
	@Positive
	@NotNull
	private Integer quantity;
	
	@Positive
	@NotNull
	private Double price;
}
