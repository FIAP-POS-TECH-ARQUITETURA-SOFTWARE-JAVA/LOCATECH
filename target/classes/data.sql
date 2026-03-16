CREATE TABLE veiculos (

    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    marca VARCHAR(255),
    modelo VARCHAR(255),
    placa VARCHAR(255),
    ano INT ,
    cor VARCHAR(255),
    valor Decimal(10,2)

);

INSERT INTO veiculos (marca,modelo,placa,ano,cor, valor)
VALUES ('chevrolet', 'celta' , 'abc1234' , '2010', 'preto', 100);