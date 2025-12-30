package com.github.deyvidsalvatore.icompras.pedidos.repository;

import com.github.deyvidsalvatore.icompras.pedidos.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
