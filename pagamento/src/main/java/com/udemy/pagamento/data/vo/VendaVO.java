package com.udemy.pagamento.data.vo;

import com.udemy.pagamento.entity.ProdutoVenda;
import com.udemy.pagamento.entity.Venda;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class VendaVO extends RepresentationModel<VendaVO> {
    private Long id;
    private LocalDate data;
    private BigDecimal valorTotal;
    private List<ProdutoVendaVO> produtos;

    public static VendaVO of(Venda venda) {
        return new ModelMapper().map(venda, VendaVO.class);
    }
}
