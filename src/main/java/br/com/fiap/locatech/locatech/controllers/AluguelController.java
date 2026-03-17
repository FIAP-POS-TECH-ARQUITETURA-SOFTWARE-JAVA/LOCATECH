package br.com.fiap.locatech.locatech.controllers;


import br.com.fiap.locatech.locatech.entities.Aluguel;
import br.com.fiap.locatech.locatech.services.AluguelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alugueis")
@Slf4j
public class AluguelController {

    private final AluguelService aluguelService;


    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
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
            @RequestBody Aluguel aluguel
    ){
        log.info("POST/alugueis " );
        aluguelService.saveAluguel(aluguel);
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


}
