package com.github.deyvidsalvatore.icompras.clientes.service;

import com.github.deyvidsalvatore.icompras.clientes.model.Cliente;
import com.github.deyvidsalvatore.icompras.clientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }

    public Optional<Cliente> buscarPorCodigo(Long codigo) {
        return repository.findById(codigo);
    }
}
