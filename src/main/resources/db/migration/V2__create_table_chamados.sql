CREATE TABLE chamados
(
    id         BIGSERIAL PRIMARY KEY,
    titulo     VARCHAR(150) NOT NULL,
    descricao  TEXT         NOT NULL,
    status     VARCHAR(20),
    usuario_id BIGINT       NOT NULL,
    CONSTRAINT fk_chamados_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
);