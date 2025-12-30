package com.github.deyvidsalvatore.icompras.clientes.repository;

import com.github.deyvidsalvatore.icompras.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
