CREATE DATABASE IF NOT EXISTS scoreinvest
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE scoreinvest;

CREATE TABLE IF NOT EXISTS cotacoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_cotacao DATE NOT NULL,
    ticker VARCHAR(20) NOT NULL,
    preco_abertura DECIMAL(18, 6) NOT NULL,
    preco_fechamento DECIMAL(18, 6) NOT NULL,
    preco_maximo DECIMAL(18, 6) NOT NULL,
    preco_minimo DECIMAL(18, 6) NOT NULL,
    volume DECIMAL(24, 4) NOT NULL,

    INDEX idx_cotacoes_data (data_cotacao),
    INDEX idx_cotacoes_ticker (ticker),
    UNIQUE KEY uk_cotacao_data_ticker (data_cotacao, ticker)
);