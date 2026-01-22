package com.github.deyvidsalvatore.icompras.pedidos.subscriber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.deyvidsalvatore.icompras.pedidos.service.AtualizacaoStatusPedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AtualizacaoStatusPedidoSubscriber {

    private final AtualizacaoStatusPedidoService service;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = {
                    "${icompras.config.kafka.topics.pedidos-faturados}",
                    "${icompras.config.kafka.topics.pedidos-enviados}"
            }
    )
    public void receberAtualizacao(String json) {
      log.info("Recebendo atualização: {}", json);

    }
}
