package com.github.deyvidsalvatore.icompras.logistica.models;

public record AtualizacaoEnvioPedido(
        Long codigo,
        StatusPedido status,
        String codigoRastreio
) {
}
