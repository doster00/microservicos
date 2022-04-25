package com.udemy.pagamento.service;

import com.udemy.pagamento.data.vo.VendaVO;
import com.udemy.pagamento.entity.ProdutoVenda;
import com.udemy.pagamento.entity.Venda;
import com.udemy.pagamento.exception.NotFoundException;
import com.udemy.pagamento.repository.ProdutoRepository;
import com.udemy.pagamento.repository.ProdutoVendaRepository;
import com.udemy.pagamento.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    protected VendaRepository vendaRepository;

    @Autowired
    protected ProdutoVendaRepository produtoVendaRepository;

    public VendaVO create(VendaVO vendaVO) {
        var vendaSalva = vendaRepository.save(Venda.of(vendaVO));
        var produtosVenda = vendaVO.getProdutos().stream().map(produtoVendaVo -> {
            ProdutoVenda produtoVenda = ProdutoVenda.of(produtoVendaVo);
            produtoVenda.setVenda(vendaSalva);
            return produtoVenda;
        }).collect(Collectors.toList());

        produtoVendaRepository.saveAll(produtosVenda);

        return VendaVO.of(vendaSalva);
    }

    public Page<VendaVO> findAll(Pageable pageable) {
        var page = vendaRepository.findAll(pageable);
        return page.map(VendaVO::of);
    }

    public VendaVO findById(Long id) {
        var venda = vendaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
        return VendaVO.of(venda);
    }

}
