package com.udemy.crud.service;

import com.udemy.crud.data.vo.ProdutoVO;
import com.udemy.crud.entity.Produto;
import com.udemy.crud.exception.NotFoundException;
import com.udemy.crud.message.ProdutoSendMessage;
import com.udemy.crud.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    protected final ProdutoRepository produtoRepository;
    protected final ProdutoSendMessage produtoSendMessage;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository,
                          ProdutoSendMessage produtoSendMessage) {
        this.produtoRepository = produtoRepository;
        this.produtoSendMessage = produtoSendMessage;
    }

    public ProdutoVO create(ProdutoVO produtoVO) {
        var produto = produtoRepository.save(Produto.of(produtoVO));
        produtoSendMessage.sendMessage(ProdutoVO.of(produto));
        return ProdutoVO.of(produto);
    }

    public Page<ProdutoVO> findAll(Pageable pageable) {
        var page = produtoRepository.findAll(pageable);
        return page.map(ProdutoVO::of);
    }

    public ProdutoVO findById(Long id) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
        return ProdutoVO.of(produto);
    }

    public ProdutoVO update(ProdutoVO produtoVO, Long id) {
        verificaSeOProdutoExiste(id);
        var produtoAtualizado = produtoRepository.save(Produto.of(produtoVO));
        return ProdutoVO.of(produtoAtualizado);
    }

    public void delete(Long id) {
        verificaSeOProdutoExiste(id);
        produtoRepository.deleteById(id);
    }

    private void verificaSeOProdutoExiste(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new NotFoundException("Produto não encontrado");
        }
    }
}
