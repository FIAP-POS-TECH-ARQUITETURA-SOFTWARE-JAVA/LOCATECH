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

CREATE TABLE pessoas (

      id BIGINT AUTO_INCREMENT PRIMARY KEY ,
      nome VARCHAR(255),
      cpf VARCHAR(255),
      telefone VARCHAR(255),
      email VARCHAR(255)

);

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