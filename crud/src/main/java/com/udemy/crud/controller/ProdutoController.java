package com.udemy.crud.controller;

import com.udemy.crud.data.vo.ProdutoVO;
import com.udemy.crud.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    protected final ProdutoService produtoService;
    protected final PagedResourcesAssembler<ProdutoVO> assembler;

    @Autowired
    public ProdutoController(ProdutoService produtoService, PagedResourcesAssembler<ProdutoVO> assembler) {
        this.produtoService = produtoService;
        this.assembler = assembler;
    }

    @GetMapping("/{id}")
    public ProdutoVO findById(@PathVariable("id") Long id) {
        var produtoVo = produtoService.findById(id);
        produtoVo.add(linkTo(methodOn(ProdutoController.class).findById(id)).withSelfRel());
        return produtoVo;
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ProdutoVO>>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                      @RequestParam(value = "limit", defaultValue = "10") int limit,
                                                                      @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        var pageProduto = produtoService.findAll(PageRequest.of(page, limit, sortDirection, "id"));
        pageProduto.forEach(p -> p.add(linkTo(methodOn(ProdutoController.class).findById(p.getId()))
                .withSelfRel()));

        PagedModel<EntityModel<ProdutoVO>> pagedModel = assembler.toModel(pageProduto);
        return ResponseEntity.ok(pagedModel);
    }

    @PostMapping
    public ProdutoVO create(@RequestBody ProdutoVO produtoVO) {
        var produtoSalvo = produtoService.create(produtoVO);
        produtoSalvo.add(linkTo(methodOn(ProdutoController.class).findById(produtoVO.getId())).withSelfRel());
        return produtoSalvo;
    }

    @PutMapping("/{id}")
    public ProdutoVO update(@PathVariable("id") Long id, @RequestBody ProdutoVO produtoVO) {
        var produtoAtualizado = produtoService.update(produtoVO, id);
        produtoAtualizado.add(linkTo(methodOn(ProdutoController.class).findById(produtoAtualizado.getId())).withSelfRel());
        return produtoAtualizado;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        produtoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
