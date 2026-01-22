package com.github.deyvidsalvatore.icompras.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class PedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidosApplication.class, args);
	}

}
