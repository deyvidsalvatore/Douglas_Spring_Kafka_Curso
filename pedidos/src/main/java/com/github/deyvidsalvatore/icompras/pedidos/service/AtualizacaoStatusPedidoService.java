package com.github.deyvidsalvatore.icompras.pedidos.service;

import com.github.deyvidsalvatore.icompras.pedidos.model.enums.StatusPedido;
import com.github.deyvidsalvatore.icompras.pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizacaoStatusPedidoService {

    private final PedidoRepository pedidoRepository;

    public void atualizarStatus(Long codigo, StatusPedido status, String urlNotaFiscal, String rastreio) {

    }
}
