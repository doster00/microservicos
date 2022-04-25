package com.udemy.pagamento.entity;

import com.udemy.pagamento.data.vo.ProdutoVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Entity
@Table(name = "produto")
@Getter
@Setter
@EqualsAndHashCode
public class Produto {

    @Id
    private Long id;

    @Column
    private Integer estoque;

    public static Produto of(ProdutoVO produtoVO) {
        return new ModelMapper().map(produtoVO, Produto.class);
    }
}
