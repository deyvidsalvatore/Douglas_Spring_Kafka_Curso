package com.github.deyvidsalvatore.icompras.logistica.subscriber.representation;

import com.github.deyvidsalvatore.icompras.logistica.models.StatusPedido;

public record AtualizacaoFaturamentoRepresentation(
        Long codigo,
        StatusPedido status,
        String urlNotaFiscal
) {
}
