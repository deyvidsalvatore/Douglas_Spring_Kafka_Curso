package com.github.deyvidsalvatore.icompras.faturamentos.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ItemPedido {

    private Long codigo;
    private String nome;
    private BigDecimal valorUnitario;
    private Integer quantidade;

    public BigDecimal getTotal() {
        return BigDecimal.valueOf(this.quantidade).multiply(this.valorUnitario);
    }
}
