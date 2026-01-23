package com.github.deyvidsalvatore.icompras.pedidos.client.represetation;

import java.math.BigDecimal;

public record ProdutoRepresentation(
        Long codigo,
        String nome,
        BigDecimal valorUnitario,
        boolean ativo
) {
}
