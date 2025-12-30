package com.github.deyvidsalvatore.icompras.pedidos.controller;

import com.github.deyvidsalvatore.icompras.pedidos.dto.NovoPedidoDTO;
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

    public ResponseEntity criar(@RequestBody NovoPedidoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
