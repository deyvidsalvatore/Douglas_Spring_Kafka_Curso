package com.github.deyvidsalvatore.icompras.pedidos.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.github.deyvidsalvatore.icompras.pedidos.client")
public class ClientsConfig {
}
