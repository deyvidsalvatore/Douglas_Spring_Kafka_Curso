package com.github.deyvidsalvatore.icompras.produtos.repository;

import com.github.deyvidsalvatore.icompras.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
