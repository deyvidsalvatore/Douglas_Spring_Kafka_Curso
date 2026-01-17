package com.github.deyvidsalvatore.icompras.pedidos.controller;

import com.github.deyvidsalvatore.icompras.pedidos.dto.AdicaoNovoPagamentoDTO;
import com.github.deyvidsalvatore.icompras.pedidos.dto.NovoPedidoDTO;
import com.github.deyvidsalvatore.icompras.pedidos.mappers.PedidoMapper;
import com.github.deyvidsalvatore.icompras.pedidos.model.ErroResposta;
import com.github.deyvidsalvatore.icompras.pedidos.model.exception.ItemNaoEncontradoException;
import com.github.deyvidsalvatore.icompras.pedidos.model.exception.ValidationException;
import com.github.deyvidsalvatore.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody NovoPedidoDTO dto) {
        try {
            var pedido = pedidoMapper.map(dto);
            var novoPedido = pedidoService.criarPedido(pedido);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido.getCodigo());
        } catch (ValidationException e) {
            var erro = new ErroResposta("Erro validação", e.getField(), e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
    }

    @PostMapping("pagamentos")
    public ResponseEntity<?> adicionarNovoPagamento(
            @RequestBody AdicaoNovoPagamentoDTO dto
    ) {
        try {
            pedidoService.adicionarNovoPagamento(dto.codigoPedido(), dto.dados(), dto.tipoPagamento());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ItemNaoEncontradoException e) {
            var erro = new ErroResposta("Item não encontrado", "codigoPedido", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
        }
    }
}
