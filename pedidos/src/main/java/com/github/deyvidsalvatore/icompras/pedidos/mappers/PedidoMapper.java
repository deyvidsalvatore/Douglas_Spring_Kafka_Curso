package com.github.deyvidsalvatore.icompras.pedidos.mappers;

import com.github.deyvidsalvatore.icompras.pedidos.dto.NovoPedidoDTO;
import com.github.deyvidsalvatore.icompras.pedidos.model.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    Pedido map(NovoPedidoDTO dto);
}
