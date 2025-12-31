package com.github.deyvidsalvatore.icompras.pedidos.model;

import com.github.deyvidsalvatore.icompras.pedidos.model.enums.TipoPagamento;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class DadosPagamento implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String dados;

    private TipoPagamento tipoPagamento;

}
