use oo2trabalhofinal;

CREATE TABLE IF NOT EXISTS users (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    matricula int NOT NULL,
    senha varchar(255) NOT NULL,
    confirmasenha varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS clientes (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    cpf varchar(255) NOT NULL,
    telefone int NOT NULL,
    email varchar(255) NOT NULL,
    porcentagem varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS estoques (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    quantidade int NOT NULL,
    dataValidade DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS compras (
    id int NOT NULL AUTO_INCREMENT,
    cliente int NOT NULL,
    estoque int NOT NULL,
    FOREIGN KEY (cliente) REFERENCES clientes(id),
    FOREIGN KEY (estoque) REFERENCES estoques(id),
    PRIMARY KEY (id)
);

