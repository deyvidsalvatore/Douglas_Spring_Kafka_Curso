package com.github.deyvidsalvatore.icompras.pedidos.model.exception;

public class ItemNaoEncontradoException extends RuntimeException {
    public ItemNaoEncontradoException(String message) {
        super(message);
    }
}
