CREATE DATABASE academia;

USE academia;

CREATE TABLE treinos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    data VARCHAR(10) NOT NULL
);

CREATE TABLE exercicios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_exercicio VARCHAR(100) NOT NULL,
    series INT NOT NULL,
    repeticoes INT NOT NULL,
    carga DECIMAL(5, 2) NOT NULL,
    treino_id INT NOT NULL,
    FOREIGN KEY (treino_id) REFERENCES treinos(id) ON DELETE CASCADE
);
