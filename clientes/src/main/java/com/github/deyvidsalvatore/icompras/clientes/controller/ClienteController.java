package com.github.deyvidsalvatore.icompras.clientes.controller;

import com.github.deyvidsalvatore.icompras.clientes.model.Cliente;
import com.github.deyvidsalvatore.icompras.clientes.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.salvar(cliente));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Cliente> buscarPorCodigo(@PathVariable Long codigo) {
        return service.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable("codigo") Long codigo){
        var cliente = service.buscarPorCodigo(codigo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente inexistente"
                ));
        service.deletar(cliente);
        return ResponseEntity.noContent().build();
    }
}
