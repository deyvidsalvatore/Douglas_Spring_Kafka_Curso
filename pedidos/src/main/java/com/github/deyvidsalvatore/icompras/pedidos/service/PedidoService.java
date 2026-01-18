package com.github.deyvidsalvatore.icompras.pedidos.service;

import com.github.deyvidsalvatore.icompras.pedidos.client.ClientesClient;
import com.github.deyvidsalvatore.icompras.pedidos.client.ProdutosClient;
import com.github.deyvidsalvatore.icompras.pedidos.client.ServicoBancarioClient;
import com.github.deyvidsalvatore.icompras.pedidos.client.represetation.ClienteRepresetation;
import com.github.deyvidsalvatore.icompras.pedidos.client.represetation.ProdutoRepresentation;
import com.github.deyvidsalvatore.icompras.pedidos.model.DadosPagamento;
import com.github.deyvidsalvatore.icompras.pedidos.model.ItemPedido;
import com.github.deyvidsalvatore.icompras.pedidos.model.Pedido;
import com.github.deyvidsalvatore.icompras.pedidos.model.enums.StatusPedido;
import com.github.deyvidsalvatore.icompras.pedidos.model.enums.TipoPagamento;
import com.github.deyvidsalvatore.icompras.pedidos.model.exception.ItemNaoEncontradoException;
import com.github.deyvidsalvatore.icompras.pedidos.publisher.PagamentoPublisher;
import com.github.deyvidsalvatore.icompras.pedidos.repository.ItemPedidoRepository;
import com.github.deyvidsalvatore.icompras.pedidos.repository.PedidoRepository;
import com.github.deyvidsalvatore.icompras.pedidos.validator.PedidoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoValidator pedidoValidator;
    private final ServicoBancarioClient servicoBancarioClient;
    private final ClientesClient apiClientes;
    private final ProdutosClient apiProdutos;
    private final PagamentoPublisher pagamentoPublisher;

    @Transactional
    public Pedido criarPedido(Pedido pedido) {
        pedidoValidator.validar(pedido);
        realizarPersistencia(pedido);
        enviarSolicitacaoPagamento(pedido);
        return pedido;
    }

    public void atualizarStatusPagamento(
            Long codigoPedido,
            String chavePagamento,
            boolean sucesso,
            String observacoes
    ) {
        Optional<Pedido> pedidoEncontrado = pedidoRepository.findByCodigoAndChavePagamento(codigoPedido, chavePagamento);
        if(pedidoEncontrado.isEmpty()) {
            String msg = String.format("Pedido não encontrado para o código %d e chave pagamento %s", codigoPedido, chavePagamento);
            log.error(msg);
            throw new RuntimeException(msg);
        }

        Pedido pedido = pedidoEncontrado.get();

        if (sucesso) {
            prepararEPublicarPedidoPago(pedido);
        } else {
            pedido.setStatus(StatusPedido.ERRO_PAGAMENTO);
            pedido.setObservacoes(observacoes);
        }

        pedidoRepository.save(pedido);
    }

    @Transactional
    public void adicionarNovoPagamento(
            Long codigoPedido,
            String dados,
            TipoPagamento tipoPagamento
    ) {
        var pedidoEncontrado =  pedidoRepository.findById(codigoPedido);

        if (pedidoEncontrado.isEmpty()) {
            var msg = String.format("Pedido com o código %d não foi encontrado!", codigoPedido);
            log.error(msg);
            throw new ItemNaoEncontradoException(msg);
        }

        var pedido = pedidoEncontrado.get();

        DadosPagamento dadosPagamento = new DadosPagamento();
        dadosPagamento.setTipoPagamento(tipoPagamento);
        dadosPagamento.setDados(dados);

        pedido.setDadosPagamento(dadosPagamento);
        pedido.setStatus(StatusPedido.REALIZADO);
        pedido.setObservacoes("Novo pagamento realizado, aguardando o processamento.");

        String novaChavePagamento = servicoBancarioClient.solicitarPagamento(pedido);
        pedido.setChavePagamento(novaChavePagamento);

        pedidoRepository.save(pedido);
    }

    public Optional<Pedido> carregarDadosCompleto(Long codigo) {
        Optional<Pedido> pedido = pedidoRepository.findById(codigo);
        pedido.ifPresent(this::carregarDadosCliente);
        pedido.ifPresent(this::carregarItensPedido);
        return pedido;
    }

    private void prepararEPublicarPedidoPago(Pedido pedido) {
        pedido.setStatus(StatusPedido.PAGO);
        carregarDadosCliente(pedido);
        carregarItensPedido(pedido);
        pagamentoPublisher.publicar(pedido);
    }

    private void carregarDadosCliente(Pedido pedido) {
        Long codigoCliente = pedido.getCodigoCliente();
        ResponseEntity<ClienteRepresetation> response = apiClientes.obterDados(codigoCliente);
        pedido.setDadosCliente(response.getBody());
    }

    private void carregarItensPedido(Pedido pedido) {
        List<ItemPedido> itens = itemPedidoRepository.findByPedido(pedido);
        pedido.setItens(itens);
        pedido.getItens().forEach(this::carregarDadosProdutos);
    }

    private void carregarDadosProdutos(ItemPedido item) {
        Long codigoProduto = item.getCodigoProduto();
        ResponseEntity<ProdutoRepresentation> response = apiProdutos.obterDados(codigoProduto);
        item.setNome(response.getBody().nome());
    }

    private void enviarSolicitacaoPagamento(Pedido pedido) {
        String chavePagamento = servicoBancarioClient.solicitarPagamento(pedido);
        pedido.setChavePagamento(chavePagamento);
    }

    private void realizarPersistencia(Pedido pedido) {
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(pedido.getItens());
    }

}
