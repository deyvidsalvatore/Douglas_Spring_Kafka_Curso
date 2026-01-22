package com.github.deyvidsalvatore.icompras.faturamentos.publisher.representation;

import com.github.deyvidsalvatore.icompras.faturamentos.model.enums.StatusPedido;

public record AtualizacaoStatusPedido(
        Long codigo,
        StatusPedido status,
        String urlNotaFiscal
) {
}
