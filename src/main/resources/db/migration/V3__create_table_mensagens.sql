CREATE TABLE mensagens
(
    id           BIGSERIAL PRIMARY KEY,
    conteudo     TEXT      NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT now(),
    usuario_id   BIGINT    NOT NULL REFERENCES usuarios (id),
    chamado_id   BIGINT    NOT NULL REFERENCES chamados (id) ON DELETE CASCADE
);