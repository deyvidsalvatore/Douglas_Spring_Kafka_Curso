package com.github.deyvidsalvatore.icompras.produtos.controller;

import com.github.deyvidsalvatore.icompras.produtos.model.Produto;
import com.github.deyvidsalvatore.icompras.produtos.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(produto));
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Produto> obterDados(@PathVariable("codigo") Long codigo) {
        return service
                .buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable("codigo") Long codigo){
        var produto = service.buscarPorCodigo(codigo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produto inexistente"
                ));
        service.deletar(produto);
        return ResponseEntity.noContent().build();
    }
}
