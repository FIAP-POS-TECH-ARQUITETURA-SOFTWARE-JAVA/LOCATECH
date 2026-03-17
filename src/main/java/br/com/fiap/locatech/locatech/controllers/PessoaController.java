package br.com.fiap.locatech.locatech.controllers;


import br.com.fiap.locatech.locatech.entities.Pessoa;
import br.com.fiap.locatech.locatech.services.PessoaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
@Slf4j
public class PessoaController {

    private final PessoaService pessoaService;


    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }


    @GetMapping
    public ResponseEntity<List<Pessoa>> findAllPessoas(
            @RequestParam("page") int page ,
            @RequestParam("size") int size
    ) {
        log.info("/pessoas ");
        return ResponseEntity.ok(pessoaService.findAllPessoas(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> findPessoa(
            @PathVariable("id") Long id
    ){
        log.info("/pessoas/" + id);
        var pessoa = pessoaService.findPessoaById(id);
        return ResponseEntity.status(HttpStatus.OK).body(pessoa);

    }

    @PostMapping
    public ResponseEntity<Void> savePessoa(
            @RequestBody Pessoa pessoa
    ){
        log.info("POST/pessoas " );
        pessoaService.savePessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePessoa(
            @PathVariable("id") long id,
            @RequestBody Pessoa pessoa
    ){
        log.info("PUT/pessoas " );
        pessoaService.updatePessoa(pessoa , id) ;
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(
            @PathVariable("id") long id
    ){
        log.info("DELETE/pessoas " );
        pessoaService.deletePessoa(id);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
