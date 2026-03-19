package br.com.fiap.locatech.locatech.controllers.handlers;

import br.com.fiap.locatech.locatech.dtos.ResourceNotFoundDTO;
import br.com.fiap.locatech.locatech.dtos.ValidationErrorDTO;
import br.com.fiap.locatech.locatech.services.execptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExcptionHandler {

    //escuta uma exception e manipula o retorno
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerResourceNotFounExecption(
            ResourceNotFoundException e

    ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
          new ResourceNotFoundDTO(e.getMessage(), HttpStatus.NOT_FOUND.value())
        );

    }

    //escuta erros de entrada de validação de DTO @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException e

    ){
        var status = HttpStatus.BAD_REQUEST;


        List<String> errors = e.getBindingResult().getFieldErrors().stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .toList();

        return ResponseEntity.status(status).body(
                new ValidationErrorDTO(errors, status.value())
        );

    }
}
