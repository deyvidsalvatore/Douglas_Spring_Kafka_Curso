package com.github.deyvidsalvatore.icompras.pedidos.mappers;

import com.github.deyvidsalvatore.icompras.pedidos.dto.ItemPedidoDTO;
import com.github.deyvidsalvatore.icompras.pedidos.model.ItemPedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {
    ItemPedido map(ItemPedidoDTO dto);
}
