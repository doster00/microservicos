package com.udemy.pagamento.repository;

import com.udemy.pagamento.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
