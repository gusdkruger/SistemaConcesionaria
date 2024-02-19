CREATE DATABASE BancoConcessionaria;
-- DROP DATABASE BancoConcessionaria;

USE BancoConcessionaria;

CREATE TABLE Concessionaria (
	id INT PRIMARY KEY AUTO_INCREMENT,
    localizacao VARCHAR(50) NOT NULL
);

CREATE TABLE Usuario (
    login VARCHAR(20) PRIMARY KEY,
    senha VARCHAR(20) NOT NULL,
    idConcessionaria INT NOT NULL,
	FOREIGN KEY (idConcessionaria) REFERENCES Concessionaria (id)
);

CREATE TABLE Cor (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(20) NOT NULL
);

INSERT INTO Cor VALUES(1, 'Branco');
INSERT INTO Cor VALUES(2, 'Preto');
INSERT INTO Cor VALUES(3, 'Laranja');
INSERT INTO Cor VALUES(4, 'Amarelo');
INSERT INTO Cor VALUES(5, 'Cinza');
INSERT INTO Cor VALUES(6, 'Verde');
INSERT INTO Cor VALUES(7, 'Azul');
INSERT INTO Cor VALUES(8, 'Vermelho');
INSERT INTO Cor VALUES(9, 'Prata');
INSERT INTO Cor VALUES(10, 'Marrom');

CREATE TABLE Fabricante (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(20) NOT NULL
);

INSERT INTO Fabricante VALUES(1, 'Porsche');
INSERT INTO Fabricante VALUES(2, 'Fiat');
INSERT INTO Fabricante VALUES(3, 'Ford');
INSERT INTO Fabricante VALUES(4, 'Chevrolet');
INSERT INTO Fabricante VALUES(5, 'Volkswagem');
INSERT INTO Fabricante VALUES(6, 'Nissan');
INSERT INTO Fabricante VALUES(7, 'Hyundai');
INSERT INTO Fabricante VALUES(8, 'Toyota');
INSERT INTO Fabricante VALUES(9, 'Honda');
INSERT INTO Fabricante VALUES(10, 'BMW');


CREATE TABLE Tipo (
	id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(15) NOT NULL
);

INSERT INTO Tipo VALUES(1, 'Esportivo');
INSERT INTO Tipo VALUES(2, 'SUV');
INSERT INTO Tipo VALUES(3, 'Seda');
INSERT INTO Tipo VALUES(4, 'Hatchback');
INSERT INTO Tipo VALUES(5, 'Picape');

CREATE TABLE Carro (
	id INT PRIMARY KEY AUTO_INCREMENT,
    idConcessionaria INT NOT NULL,
    modelo VARCHAR(30) NOT NULL,
    cor INT NOT NULL,
    ano INT(4) NOT NULL,
    fabricante INT NOT NULL,
    tipo INT NOT NULL,
    kilometragem INT(7) NOT NULL,
    dataCompra DATE NOT NULL,
    precoCompra DECIMAL(11, 2) NOT NULL,
    vendido BOOLEAN NOT NULL,
    dataVenda DATE,
    precoVenda DECIMAL(11, 2),
    FOREIGN KEY (idConcessionaria) REFERENCES Concessionaria (id),
    FOREIGN KEY (cor) REFERENCES Cor (id),
    FOREIGN KEY (fabricante) REFERENCES Fabricante (id),
    FOREIGN KEY (tipo) REFERENCES Tipo (id)
);

INSERT INTO Concessionaria VALUES(0, 'Concessionaria Teste');
INSERT INTO Usuario VALUES('admin', 'admin', 1);
INSERT INTO Carro VALUES(0, 1, '718 Cayman', 1, 2024, 1, 1, 0, '2023-10-01', 519000, false, null, null);
INSERT INTO Carro VALUES(0, 1, '911 Turbo S', 4, 2022, 1, 1, 2600, '2023-08-13', 800000, true, '2023-10-16', 920000);
INSERT INTO Carro VALUES(0, 1, 'Amarok', 7, 2023, 5, 5, 53000, '2023-08-13', 200000, true, '2023-10-16', 225000);
INSERT INTO Carro VALUES(0, 1, 'Polo', 6, 2020, 5, 4, 97000, '2021-08-13', 100000, false, null, null);
INSERT INTO Carro VALUES(0, 1, 'Civic', 9, 2022, 9, 3, 12000, '2023-05-20', 80000, false, null, null);
INSERT INTO Carro VALUES(0, 1, 'X5', 10, 2023, 10, 2, 1500, '2023-08-30', 180000, false, null, null);
INSERT INTO Carro VALUES(0, 1, 'Hilux', 6, 2023, 8, 5, 1500, '2023-09-15', 140000, false, null, null);
INSERT INTO Carro VALUES(0, 1, 'Fiesta', 6, 2021, 3, 4, 42000, '2021-12-10', 60000, true, '2023-01-15', 68000);
INSERT INTO Carro VALUES(0, 1, 'Golf', 7, 2022, 5, 4, 15000, '2022-05-15', 90000, false, null, null);
INSERT INTO Carro VALUES(0, 1, 'Cayenne', 1, 2023, 1, 2, 4000, '2023-07-15', 220000, false, null, null);
INSERT INTO Carro VALUES(0, 1, 'Uno', 2, 2023, 2, 4, 10000, '2023-09-01', 40000, false, null, null);
INSERT INTO Carro VALUES(0, 1, 'Mobi', 3, 2022, 2, 4, 8000, '2023-02-15', 35000, false, null, null);
INSERT INTO Carro VALUES(0, 1, 'Strada', 5, 2023, 2, 5, 5000, '2023-10-10', 55000, false, null, null);
INSERT INTO Carro VALUES(0, 1, 'Fusion', 8, 2023, 3, 3, 2000, '2023-06-20', 65000, false, null, null);
INSERT INTO Carro VALUES(0, 1, 'EcoSport', 2, 2022, 3, 2, 15000, '2023-03-10', 55000, false, null, null);
INSERT INTO Carro VALUES(0, 1, 'Ranger', 4, 2023, 3, 5, 10000, '2023-07-05', 70000, false, null, null);
INSERT INTO Carro VALUES(0, 1, 'Cruze', 7, 2023, 4, 3, 5000, '2023-09-10', 60000, false, null, null);
INSERT INTO Carro VALUES(0, 1, 'Onix', 1, 2022, 4, 4, 12000, '2023-01-30', 55000, false, null, null);
INSERT INTO Carro VALUES(0, 1, 'S10', 6, 2023, 4, 5, 8000, '2023-05-25', 75000, false, null, null);