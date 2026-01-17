package com.github.deyvidsalvatore.icompras.pedidos.model;

public record ErroResposta(
        String erroValidacao,
        String campo,
        String mensagem

) {
}
