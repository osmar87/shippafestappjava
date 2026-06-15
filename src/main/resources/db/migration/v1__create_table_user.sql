-- V1__create_tables.sql
-- Flyway migration for ShippaFest
CREATE
EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE users
(
    id        UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    nome      VARCHAR(255) NOT NULL,
    nasc      TIMESTAMP    NOT NULL,
    email     VARCHAR(255) NOT NULL UNIQUE,
    senha     VARCHAR(255) NOT NULL,
    sexo      VARCHAR(50)  NOT NULL,
    genero    VARCHAR(100) NOT NULL,
    perfil    VARCHAR(100) NOT NULL,
    cidade    VARCHAR(255),
    estado    VARCHAR(255),
    bio       TEXT,
    telefone  VARCHAR(50),
    latitude  NUMERIC(10, 7),
    longitude NUMERIC(10, 7),
    whatsapp  BOOLEAN      NOT NULL DEFAULT FALSE,
    status    BOOLEAN      NOT NULL DEFAULT FALSE,
    type      INTEGER      NOT NULL DEFAULT 2,
    createat  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    signo     VARCHAR(100)
);



--
-- CREATE TABLE config
-- (
--     id             UUID PRIMARY KEY DEFAULT gen_random_uuid(),
--     id_user        UUID    NOT NULL UNIQUE REFERENCES users (id) ON DELETE CASCADE,
--     is_notificacao BOOLEAN NOT NULL DEFAULT TRUE,
--     is_my_curtiu   BOOLEAN NOT NULL DEFAULT FALSE
-- );