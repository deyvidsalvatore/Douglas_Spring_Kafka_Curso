package com.github.deyvidsalvatore.icompras.clientes.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "clientes")
@Data
public class Cliente implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(length = 150, nullable = false)
    private String nome;

    @Column(length = 11, nullable = false)
    private String cpf;

    @Column(length= 100)
    private String logradouro;

    @Column(length = 10)
    private String numero;

    @Column(length = 100)
    private String bairro;

    @Column(length = 150, unique = true)
    private String email;

    @Column(length = 20)
    private String telefone;

    @Column(name = "ativo")
    private boolean ativo;

    @PrePersist
    public void prePersist() {
        setAtivo(true);
    }
}
