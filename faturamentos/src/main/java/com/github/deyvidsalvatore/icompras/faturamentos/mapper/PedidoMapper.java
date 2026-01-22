package com.github.deyvidsalvatore.icompras.faturamentos.mapper;

import com.github.deyvidsalvatore.icompras.faturamentos.model.Cliente;
import com.github.deyvidsalvatore.icompras.faturamentos.model.ItemPedido;
import com.github.deyvidsalvatore.icompras.faturamentos.model.Pedido;
import com.github.deyvidsalvatore.icompras.faturamentos.subscriber.representation.DetalheItemPedidoRepresentation;
import com.github.deyvidsalvatore.icompras.faturamentos.subscriber.representation.DetalhePedidoRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper {

    public Pedido map(DetalhePedidoRepresentation representation) {
        Cliente cliente = new Cliente(
                representation.nome(), representation.cpf(), representation.logradouro(),
                representation.numero(), representation.bairro(), representation.email(),
                representation.telefone()
        );

        List<ItemPedido> itens = representation.itens().stream()
                .map(this::mapItem).toList();

        return new Pedido(
                representation.codigo(),
                cliente,
                representation.dataPedido(),
                representation.total(),
                itens
        );
    }

    private ItemPedido mapItem(DetalheItemPedidoRepresentation representation) {
        return new ItemPedido(
                representation.codigoProduto(), representation.nome(),
                representation.valorUnitario(), representation.quantidade()
        );
    }
}
