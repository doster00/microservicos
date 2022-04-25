package com.udemy.pagamento.data.vo;

import com.udemy.pagamento.entity.Produto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class ProdutoVO extends RepresentationModel<ProdutoVO> {
    private Long id;
    private Integer estoque;

    public static ProdutoVO of(Produto produto) {
        return new ModelMapper().map(produto, ProdutoVO.class);
    }
}
