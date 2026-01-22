package com.github.deyvidsalvatore.icompras.faturamentos.subscriber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.deyvidsalvatore.icompras.faturamentos.mapper.PedidoMapper;
import com.github.deyvidsalvatore.icompras.faturamentos.service.GeradorNotaFiscalService;
import com.github.deyvidsalvatore.icompras.faturamentos.subscriber.representation.DetalhePedidoRepresentation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PedidoPagoSubscriber {

    private final ObjectMapper mapper;
    private final GeradorNotaFiscalService geradorNotaFiscalService;
    private final PedidoMapper pedidoMapper;

    @KafkaListener(
            groupId = "icompras-faturamento",
            topics = "${icompras.config.kafka.topics.pedidos-pagos}"
    )
    public void listen(String json) {
        try {
            log.info("Recebendo pedido para faturamento: {}", json);
            var detalhePedidoRepresentation = mapper.readValue(json, DetalhePedidoRepresentation.class);
            var pedido =  pedidoMapper.map(detalhePedidoRepresentation);
            this.geradorNotaFiscalService.gerar(pedido);
        } catch (Exception e) {
            log.error("Erro na consumação do tópico de pedidos pagos {}", e.getMessage());
            throw new RuntimeException(e);

        }
    }
}
