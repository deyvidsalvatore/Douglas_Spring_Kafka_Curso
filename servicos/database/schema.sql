-- Create Database for each microservice
CREATE DATABASE icompraprodutos;
CREATE DATABASE icompraclientes;
CREATE DATABASE icomprapedidos;

-- ICompraClientes
CREATE TABLE clientes (
                          codigo serial not null primary key,
                          nome varchar(150) not null,
                          cpf char(11) not null,
                          logradouro varchar(100),
                          numero varchar(10),
                          bairro varchar(100),
                          email varchar(150),
                          telefone varchar(20)
);

SELECT * FROM clientes;

-- ICompraProdutos
CREATE TABLE produtos (
                          codigo serial NOT NULL PRIMARY KEY,
                          nome varchar(100) NOT NULL,
                          valor_unitario decimal(16, 2) NOT NULL
);



-- ICompraPedidos
CREATE TABLE pedido(
                       codigo serial NOT NULL PRIMARY KEY,
                       codigo_cliente bigint NOT NULL,
                       data_pedido timestamp NOT NULL DEFAULT now(),
                       chave_pagamento text,
                       observacoes text,
                       status varchar(20) CHECK (
                           status IN ('REALIZADO', 'PAGO', 'FATURADO', 'ENVIADO', 'ERRO_PAGAMENTO', 'PREPARANDO_ENVIO')
                           ),
                       total decimal(16,2) NOT NULL,
                       codigo_rastreio VARCHAR(255),
                       url_nf TEXT
);

CREATE TABLE item_pedido (
                             codigo serial NOT NULL PRIMARY KEY,
                             codigo_pedido bigint NOT NULL REFERENCES pedido(codigo),
                             codigo_produto bigint NOT NULL,
                             quantidade int NOT NULL,
                             valor_unitario decimal(16,2) NOT NULL
);
