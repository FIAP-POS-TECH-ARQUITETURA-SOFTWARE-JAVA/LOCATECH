CREATE TABLE veiculos (

    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    marca VARCHAR(255) NOT NULL ,
    modelo VARCHAR(255) NOT NULL ,
    placa VARCHAR(255),
    ano INT ,
    cor VARCHAR(255),
    valor Decimal(10,2)

);

INSERT INTO veiculos (marca,modelo,placa,ano,cor, valor)
VALUES ('chevrolet', 'celta' , 'abc1234' , '2010', 'preto', 100);

CREATE TABLE pessoas (

      id BIGINT AUTO_INCREMENT PRIMARY KEY ,
      nome VARCHAR(255),
      cpf VARCHAR(255),
      telefone VARCHAR(255),
      email VARCHAR(255)

);

INSERT INTO pessoas (nome,cpf,telefone,email )
VALUES ('Deivis Vargas', '25054877478' , '988144555' , 'deivisVp@yahoo.com.br');


CREATE TABLE alugueis (

    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    pessoa_id  BIGINT NOT NULL ,
    veiculo_id  BIGINT NOT NULL ,
    data_inicio DATE ,
    data_fim DATE ,
    valor_total Decimal(10,2) ,
    FOREIGN KEY (pessoa_id) references pessoas(id),
    FOREIGN KEY (veiculo_id) references veiculos(id)

);

INSERT INTO alugueis (pessoa_id,veiculo_id,data_inicio,data_fim , valor_total )
VALUES (1,1, '2026-01-01' , '2026-01-10' , 1200);
