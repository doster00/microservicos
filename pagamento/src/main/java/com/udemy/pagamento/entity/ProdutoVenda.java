package com.udemy.pagamento.entity;

import com.udemy.pagamento.data.vo.ProdutoVendaVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Entity
@Table(name = "produto_venda")
@Getter
@Setter
@EqualsAndHashCode
public class ProdutoVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_produto", nullable = false)
    private Long idProduto;

    @Column
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    public static ProdutoVenda of(ProdutoVendaVO produtoVendaVO) {
        return new ModelMapper().map(produtoVendaVO, ProdutoVenda.class);
    }
}
