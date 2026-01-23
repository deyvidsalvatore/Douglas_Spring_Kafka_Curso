package com.github.deyvidsalvatore.icompras.pedidos.service;

import com.github.deyvidsalvatore.icompras.pedidos.model.enums.StatusPedido;
import com.github.deyvidsalvatore.icompras.pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AtualizacaoStatusPedidoService {

    private final PedidoRepository pedidoRepository;

    @Transactional
    public void atualizarStatus(Long codigo, StatusPedido status, String urlNotaFiscal, String rastreio) {
        this.pedidoRepository.findById(codigo).ifPresent(pedido -> {
            pedido.setStatus(status);
            pedido.setUrlNotaFiscal(urlNotaFiscal);
            pedido.setCodigoRastreio(rastreio);
        });
    }
}
