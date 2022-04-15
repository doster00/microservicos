package com.udemy.crud.data.vo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.udemy.crud.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoVO extends RepresentationModel<ProdutoVO> {
    private Long id;
    private String nome;
    private Integer estoque;
    private BigDecimal preco;

    public static ProdutoVO of(Produto produto) {
        return new ModelMapper().map(produto, ProdutoVO.class);
    }
}
