package com.github.deyvidsalvatore.icompras.pedidos.config;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaStartupValidator {

    @Value("${icompras.config.kafka.server-url}")
    private String kafkaServerUrl;

    @PostConstruct
    public void validateKafkaConnection() {
        try (AdminClient adminClient = AdminClient.create(
                Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerUrl))) {
            adminClient.describeCluster().clusterId().get();

        } catch (Exception e) {
            throw new IllegalStateException("❌ Kafka NÃO está disponível", e);
        }
    }
}

