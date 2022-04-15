package com.udemy.crud.entity;

import com.udemy.crud.data.vo.ProdutoVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Getter
@Setter
@EqualsAndHashCode
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false)
    private Integer estoque;

    @Column(nullable = false)
    private BigDecimal preco;

    public static Produto of(ProdutoVO produtoVO) {
        return new ModelMapper().map(produtoVO, Produto.class);
    }
}
