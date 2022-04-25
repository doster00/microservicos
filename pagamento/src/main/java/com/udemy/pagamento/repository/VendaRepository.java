package com.udemy.pagamento.repository;

import com.udemy.pagamento.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
