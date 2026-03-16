package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.entities.Veiculo;
import br.com.fiap.locatech.locatech.services.VeiculoService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/veiculos" )
@Slf4j
public class VeiculoController {

    private  final VeiculoService veiculoService ;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> findAllVeiculos(
            @RequestParam("page") int page ,
            @RequestParam("size") int size
    ) {
        log.info("/veiculos ");
        return ResponseEntity.ok(veiculoService.findAllVeiculos(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Veiculo>> findVeiculo(
            @PathVariable("id") Long id
    ){
        log.info("/veiculos/" + id);
        var veiculo = veiculoService.findVeiculoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(veiculo);

    }

    @PostMapping
    public ResponseEntity<Void> saveVeiculo(
            @RequestBody Veiculo veiculo
    ){
        log.info("POST/veiculos " );
        veiculoService.saveVeiculo(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(
            @PathVariable("id") long id,
            @RequestBody Veiculo veiculo
    ){
        log.info("PUT/veiculos " );
        veiculoService.updateVeiculo(veiculo , id) ;
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(
            @PathVariable("id") long id
    ){
        log.info("DELETE/veiculos " );
        veiculoService.deleteVeiculo(id);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
