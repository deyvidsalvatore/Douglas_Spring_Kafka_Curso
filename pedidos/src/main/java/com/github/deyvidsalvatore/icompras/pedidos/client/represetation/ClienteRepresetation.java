package com.github.deyvidsalvatore.icompras.pedidos.client.represetation;

public record ClienteRepresetation(
        Long codigo,
        String nome,
        String cpf,
        String logradouro,
        String numero,
        String bairro,
        String email,
        String telefone,
        boolean ativo
) {
}
