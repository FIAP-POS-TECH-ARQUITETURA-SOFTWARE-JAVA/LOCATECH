package br.com.fiap.locatech.locatech.repositories;

import br.com.fiap.locatech.locatech.entities.Veiculo;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Hidden
public class VeiculoRepositoryImp implements VeiculoRepository {


    private final JdbcClient jdbcClient;

    public VeiculoRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }



    @Override
    public Optional<Veiculo> findById(Long id) {
        return this.jdbcClient
                .sql("Select * from veiculos where id = :id")
                .param("id" , id)
                .query(Veiculo.class)
                .optional();

    }

    @Override
    public List<Veiculo> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("Select * from veiculos LIMIT :size OFFSET :offset")
                .param("size" , size)
                .param("offset", offset)
                .query(Veiculo.class)
                .list();
    }

    @Override
    public Integer save(Veiculo veiculo) {
        return this.jdbcClient
                .sql("INSERT INTO veiculos(marca, modelo,placa,ano,cor,valor) VALUES (:marca, :modelo, :placa, :ano, :cor, :valor )")
                .param("marca" , veiculo.getMarca())
                .param("modelo" , veiculo.getModelo())
                .param("placa" , veiculo.getPlaca())
                .param("ano" , veiculo.getAno())
                .param("cor" , veiculo.getCor())
                .param("valor" , veiculo.getValor())
                .update();

    }

    @Override
    public Integer update(Veiculo veiculo, Long id) {

        return this.jdbcClient
                .sql("UPDATE  veiculos set marca = :marca , modelo = :modelo , placa = :placa , ano = :ano, valor = :valor  where id = :id")
                .param("marca" , veiculo.getMarca())
                .param("modelo" , veiculo.getModelo())
                .param("placa" , veiculo.getPlaca())
                .param("ano" , veiculo.getAno())
                .param("cor" , veiculo.getCor())
                .param("valor" , veiculo.getValor())
                .param("id", id)
                .update();
    }

    @Override
    public Integer delete(Long id) {

        return this.jdbcClient
                .sql("DELETE from  veiculos  where id = :id")
                .param("id", id)
                .update();
    }
}
