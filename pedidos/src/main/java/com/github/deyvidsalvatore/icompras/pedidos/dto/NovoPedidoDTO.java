package com.github.deyvidsalvatore.icompras.pedidos.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class NovoPedidoDTO implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    private Long codigoCliente;

}
