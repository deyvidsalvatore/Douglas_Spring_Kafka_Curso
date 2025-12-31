package com.github.deyvidsalvatore.icompras.pedidos.dto;

import com.github.deyvidsalvatore.icompras.pedidos.model.enums.TipoPagamento;

public record DadosPagamentoDTO(String dados, TipoPagamento tipoPagamento) {
}