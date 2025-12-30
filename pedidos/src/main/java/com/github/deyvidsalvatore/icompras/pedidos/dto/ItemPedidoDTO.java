package com.github.deyvidsalvatore.icompras.pedidos.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class ItemPedidoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long codigoCliente;

    private Integer quantidade;

    private BigDecimal valorUnitario;

}
