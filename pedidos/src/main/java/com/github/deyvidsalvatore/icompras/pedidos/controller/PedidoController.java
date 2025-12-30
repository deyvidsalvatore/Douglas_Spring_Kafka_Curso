package com.github.deyvidsalvatore.icompras.pedidos.controller;

import com.github.deyvidsalvatore.icompras.pedidos.dto.NovoPedidoDTO;
import com.github.deyvidsalvatore.icompras.pedidos.mappers.PedidoMapper;
import com.github.deyvidsalvatore.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{pedidos}")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    public ResponseEntity<Long> criar(@RequestBody NovoPedidoDTO dto) {
        var pedido = pedidoMapper.map(dto);
        var novoPedido = pedidoService.criarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido.getCodigo());
    }
}
