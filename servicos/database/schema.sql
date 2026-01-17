CREATE DATABASE icompraprodutos;
CREATE DATABASE icompraclientes;
CREATE DATABASE icomprapedidos;

\c icompraclientes

CREATE TABLE clientes (
                          codigo serial NOT NULL PRIMARY KEY,
                          nome varchar(150) NOT NULL,
                          cpf char(11) NOT NULL,
                          logradouro varchar(100),
                          numero varchar(10),
                          bairro varchar(100),
                          email varchar(150),
                          telefone varchar(20)
);

INSERT INTO clientes (nome, cpf, logradouro, numero, bairro, email, telefone)
VALUES
    ('João Silva', '12345678901', 'Rua das Flores', '100', 'Centro', 'joao.silva@email.com', '11999990001'),
    ('Maria Oliveira', '23456789012', 'Av. Brasil', '250', 'Jardins', 'maria.oliveira@email.com', '11999990002'),
    ('Carlos Pereira', '34567890123', 'Rua Afonso Pena', '45', 'Vila Nova', 'carlos.p@email.com', '11999990003');

\c icompraprodutos

CREATE TABLE produtos (
                          codigo serial NOT NULL PRIMARY KEY,
                          nome varchar(100) NOT NULL,
                          valor_unitario decimal(16, 2) NOT NULL
);

INSERT INTO produtos (nome, valor_unitario)
VALUES
    ('Notebook Dell Inspiron', 4500.00),
    ('Mouse Logitech', 120.50),
    ('Teclado Mecânico', 350.90),
    ('Monitor 27 Polegadas', 1899.99);

\c icomprapedidos

CREATE TABLE pedido (
                        codigo serial NOT NULL PRIMARY KEY,
                        codigo_cliente bigint NOT NULL,
                        data_pedido timestamp NOT NULL DEFAULT now(),
                        chave_pagamento text,
                        observacoes text,
                        status varchar(20) CHECK (
                            status IN (
                                       'REALIZADO',
                                       'PAGO',
                                       'FATURADO',
                                       'ENVIADO',
                                       'ERRO_PAGAMENTO',
                                       'PREPARANDO_ENVIO'
                                )
                            ),
                        total decimal(16,2) NOT NULL,
                        codigo_rastreio varchar(255),
                        url_nf text
);

CREATE TABLE item_pedido (
                             codigo serial NOT NULL PRIMARY KEY,
                             codigo_pedido bigint NOT NULL REFERENCES pedido(codigo),
                             codigo_produto bigint NOT NULL,
                             quantidade int NOT NULL,
                             valor_unitario decimal(16,2) NOT NULL
);

INSERT INTO pedido
(codigo_cliente, chave_pagamento, observacoes, status, total, codigo_rastreio, url_nf)
VALUES
    (1, 'PIX123456', 'Entregar em horário comercial', 'PAGO', 4620.50, 'BR123456789', 'http://nf.exemplo.com/nf/1'),
    (2, 'CARTAO987654', 'Cliente prefere entrega rápida', 'PREPARANDO_ENVIO', 2250.89, NULL, NULL);

INSERT INTO item_pedido
(codigo_pedido, codigo_produto, quantidade, valor_unitario)
VALUES
    (1, 1, 1, 4500.00),
    (1, 2, 1, 120.50),
    (2, 4, 1, 1899.99),
    (2, 3, 1, 350.90);
