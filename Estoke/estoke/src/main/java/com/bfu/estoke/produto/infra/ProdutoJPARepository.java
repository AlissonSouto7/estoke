package com.bfu.estoke.produto.infra;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bfu.estoke.produto.domain.Produto;

public interface ProdutoJPARepository extends JpaRepository<Produto, UUID>{

	List<Produto> findAllByUser_IdUser(UUID idUser);

}
