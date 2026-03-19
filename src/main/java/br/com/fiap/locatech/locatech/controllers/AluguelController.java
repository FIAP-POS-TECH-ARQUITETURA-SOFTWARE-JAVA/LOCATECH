package br.com.fiap.locatech.locatech.controllers;


import br.com.fiap.locatech.locatech.dtos.AluguelRequestDTO;
import br.com.fiap.locatech.locatech.entities.Aluguel;
import br.com.fiap.locatech.locatech.entities.Veiculo;
import br.com.fiap.locatech.locatech.services.AluguelService;
import br.com.fiap.locatech.locatech.services.VeiculoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alugueis")
@Slf4j
public class AluguelController {

    private final AluguelService aluguelService;
    private final VeiculoService veiculoService;


    public AluguelController(AluguelService aluguelService, VeiculoService veiculoService) {
        this.aluguelService = aluguelService;
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public ResponseEntity<List<Aluguel>> findAllAluguels(
            @RequestParam("page") int page ,
            @RequestParam("size") int size
    ) {
        log.info("/alugueis ");
        return ResponseEntity.ok(aluguelService.findAllAlugueis(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluguel>> findAluguel(
            @PathVariable("id") Long id
    ){
        log.info("/alugueis/" + id);
        var aluguel = aluguelService.findAluguelById(id);
        return ResponseEntity.status(HttpStatus.OK).body(aluguel);

    }

    @PostMapping
    public ResponseEntity<Void> saveAluguel(
            @RequestBody @Valid AluguelRequestDTO aluguel
    ){
        log.info("POST/alugueis " );
        aluguelService.saveAluguel(calcularAluguel(aluguel));
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAluguel(
            @PathVariable("id") long id,
            @RequestBody Aluguel aluguel
    ){
        log.info("PUT/alugueis " );
        aluguelService.updateAluguel(aluguel , id) ;
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluguel(
            @PathVariable("id") long id
    ){
        log.info("DELETE/alugueis " );
        aluguelService.deleteAluguel(id);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    private Aluguel calcularAluguel(AluguelRequestDTO aluguelRequestDTO){

        Veiculo veiculo = veiculoService.findVeiculoById(aluguelRequestDTO.veiculoId())
                .orElseThrow(
                        () -> new RuntimeException("Veículo não encontrado")
                );
        var quantidadeDias = BigDecimal.valueOf(aluguelRequestDTO.dataFim().getDayOfYear() - aluguelRequestDTO.dataInicio().getDayOfYear());
        var valor = veiculo.getValor().multiply(quantidadeDias)  ;

        return  new Aluguel(aluguelRequestDTO, valor);

    }


}
