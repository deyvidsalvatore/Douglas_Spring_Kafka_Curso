package com.github.deyvidsalvatore.icompras.pedidos.repository;

import com.github.deyvidsalvatore.icompras.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
