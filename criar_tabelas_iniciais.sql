CREATE TABLE usuario
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(50)  NOT NULL
);


CREATE TABLE produto
(
    id                    BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome                  VARCHAR(255)   NOT NULL,
    descricao             TEXT,
    preco                 DECIMAL(10, 2) NOT NULL,
    quantidade_em_estoque BIGINT         NOT NULL,
    categoria             VARCHAR(255)   NOT NULL
);

CREATE TABLE pedido
(
    id                       BIGINT AUTO_INCREMENT PRIMARY KEY,
    status_pedido            VARCHAR(50)    NOT NULL,
    valor_total              DECIMAL(10, 2) NOT NULL,
    data_criacao_pedido      TIMESTAMP      NOT NULL,
    data_pagamento_pedido    TIMESTAMP      NULL,
    data_cancelamento_pedido TIMESTAMP      NULL,
    usuario_id               BIGINT         NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario (id)
);

CREATE TABLE item_pedido
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_id  BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INT    NOT NULL,

    CONSTRAINT fk_item_pedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedido (id) ON DELETE CASCADE,
    CONSTRAINT fk_item_pedido_produto FOREIGN KEY (produto_id) REFERENCES produto (id)
);

INSERT INTO usuario (username, password, role)
VALUES ('admin', '$2a$10$E5EpSXIned/lDa7fFDB.xeC9VRCZY/uKBBLbciAvu8GcPn88HMv3e', 'ROLE_ADMIN'),
       ('bruno', '$2a$10$E5EpSXIned/lDa7fFDB.xeC9VRCZY/uKBBLbciAvu8GcPn88HMv3e', 'ROLE_USER');

