package com.udemy.pagamento.entity;

import com.udemy.pagamento.data.vo.VendaVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "venda")
@Getter
@Setter
@EqualsAndHashCode
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate data;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    public static Venda of(VendaVO vendaVO) {
        return new ModelMapper().map(vendaVO, Venda.class);
    }
}
