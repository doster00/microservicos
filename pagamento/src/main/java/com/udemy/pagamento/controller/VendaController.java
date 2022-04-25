package com.udemy.pagamento.controller;


import com.udemy.pagamento.data.vo.VendaVO;
import com.udemy.pagamento.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("vendas")
public class VendaController {

    protected final VendaService vendaService;
    protected final PagedResourcesAssembler<VendaVO> assembler;

    @Autowired
    public VendaController(VendaService vendaService, PagedResourcesAssembler<VendaVO> assembler) {
        this.vendaService = vendaService;
        this.assembler = assembler;
    }

    @GetMapping("/{id}")
    public VendaVO findById(@PathVariable("id") Long id) {
        var vendaVO = vendaService.findById(id);
        vendaVO.add(linkTo(methodOn(VendaController.class).findById(id)).withSelfRel());
        return vendaVO;
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<VendaVO>>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                    @RequestParam(value = "limit", defaultValue = "10") int limit,
                                                                    @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        var pageVenda = vendaService.findAll(PageRequest.of(page, limit, sortDirection, "id"));
        pageVenda.forEach(p -> p.add(linkTo(methodOn(VendaController.class).findById(p.getId())).withSelfRel()));

        PagedModel<EntityModel<VendaVO>> pagedModel = assembler.toModel(pageVenda);
        return ResponseEntity.ok(pagedModel);
    }

    @PostMapping
    public VendaVO create(@RequestBody VendaVO vendaVO) {
        var vendaSalva = vendaService.create(vendaVO);
        vendaSalva.add(linkTo(methodOn(VendaController.class).findById(vendaSalva.getId())).withSelfRel());
        return vendaSalva;
    }
}
