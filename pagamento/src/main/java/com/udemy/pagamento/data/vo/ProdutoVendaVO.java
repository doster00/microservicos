package com.udemy.pagamento.data.vo;

import com.udemy.pagamento.entity.ProdutoVenda;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class ProdutoVendaVO extends RepresentationModel<ProdutoVendaVO> {

    private Long id;
    private Long idProduto;
    private Integer quantidade;

    public static ProdutoVendaVO of(ProdutoVenda produtoVenda) {
        return new ModelMapper().map(produtoVenda, ProdutoVendaVO.class);
    }
}
