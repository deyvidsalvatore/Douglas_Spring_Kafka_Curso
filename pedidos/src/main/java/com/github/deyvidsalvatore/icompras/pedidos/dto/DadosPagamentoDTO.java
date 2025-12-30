package com.github.deyvidsalvatore.icompras.pedidos.dto;

import com.github.deyvidsalvatore.icompras.pedidos.model.enums.TipoPagamento;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class DadosPagamentoDTO implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    private String dados;

    private TipoPagamento tipoPagamento;
}
