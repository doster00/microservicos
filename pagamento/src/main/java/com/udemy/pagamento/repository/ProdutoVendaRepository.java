package com.udemy.pagamento.repository;

import com.udemy.pagamento.entity.ProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Long> {
}
