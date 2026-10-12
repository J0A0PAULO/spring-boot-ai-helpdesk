CREATE TABLE usuarios
(
    id    BIGSERIAL PRIMARY KEY,
    nome  VARCHAR(100)        NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    senha VARCHAR(255)        NOT NULL
);

CREATE TABLE usuarios_roles
(
    usuarios_id BIGINT      NOT NULL,
    roles       VARCHAR(50) NOT NULL,
    PRIMARY KEY (usuarios_id, roles),
    CONSTRAINT fk_usuarios_roles_usuario
        FOREIGN KEY (usuarios_id) REFERENCES usuarios (id) ON DELETE CASCADE
);