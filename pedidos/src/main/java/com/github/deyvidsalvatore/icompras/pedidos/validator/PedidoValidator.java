package com.github.deyvidsalvatore.icompras.pedidos.validator;

import com.github.deyvidsalvatore.icompras.pedidos.client.ClientesClient;
import com.github.deyvidsalvatore.icompras.pedidos.client.ProdutosClient;
import com.github.deyvidsalvatore.icompras.pedidos.client.represetation.ClienteRepresetation;
import com.github.deyvidsalvatore.icompras.pedidos.client.represetation.ProdutoRepresentation;
import com.github.deyvidsalvatore.icompras.pedidos.model.ItemPedido;
import com.github.deyvidsalvatore.icompras.pedidos.model.Pedido;
import com.github.deyvidsalvatore.icompras.pedidos.model.exception.ValidationException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoValidator {

    private final ProdutosClient produtosClient;
    private final ClientesClient clientesClient;

    public void validar(Pedido pedido) {
        Long codigoCliente = pedido.getCodigoCliente();
        validarCliente(codigoCliente);
        pedido.getItens().forEach(this::validarItem);
    }

    private void validarCliente(Long codigoCliente) {
        try {
          ResponseEntity<ClienteRepresetation> response = clientesClient.obterDados(codigoCliente);
          ClienteRepresetation clienteRepresetation = response.getBody();
          log.info("Cliente de código {} encontrando: {}", clienteRepresetation.codigo(), clienteRepresetation.nome());

          if(!clienteRepresetation.ativo()) {
              throw new ValidationException("codigoCliente", "Cliente inválido!");
          }
        } catch (FeignException.NotFound e) {
            log.error("Cliente não encontrado");
            var message = String.format("Cliente de código %d não encontrado", codigoCliente);
            throw new ValidationException("codigoCliente", message);
        }

    }

    private void validarItem(ItemPedido item) {
        try {
            ResponseEntity<ProdutoRepresentation> response = produtosClient.obterDados(item.getCodigoProduto());
            ProdutoRepresentation produtoRepresentation = response.getBody();
            log.info("Produto de código {} encontrado: {}", produtoRepresentation.codigo(), produtoRepresentation.nome());
            if(!produtoRepresentation.ativo()) {
                throw new ValidationException("codigoProduto", "Produto inativo!");
            }
        } catch (FeignException.NotFound e) {
            log.error("Produto não encontrado");
            var message = String.format("Produto de código %d não encontrado", item.getCodigoProduto());
            throw new ValidationException("codigoProduto", message);
        }
    }
}
