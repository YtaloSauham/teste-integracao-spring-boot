package com.example.testeIntegracaoSpring.controller;

import com.example.testeIntegracaoSpring.model.Frete;
import com.example.testeIntegracaoSpring.service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/fretes")
public class FreteController {
    @Autowired
    private FreteService service;

    @GetMapping("/")
    public ResponseEntity<Frete> listarTodos(){
        var optional = service.todos();

        if (!optional.isEmpty()){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Frete> buscaPor(@PathVariable Integer id) {
        var optional = service.buscaPor(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Frete> buscaPorCliente_Id(@PathVariable Integer id) {
        var optional = service.buscaPorCliente_Id(id);

        if (optional != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cidade/{id}")
    public ResponseEntity<Frete> buscaPorCidade_Id(@PathVariable Integer id) {
        var optional = service.buscaPorCidade_Id(id);

        if (optional != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/order/{id}")
    public ResponseEntity<Frete> buscarPorCliente_IdOrderByValcrAsc(@PathVariable Integer id) {
        var optional = service.buscarPorCliente_IdOrderByValcrAsc(id);

        if (optional != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/inserir")
    public ResponseEntity<Frete> cadastro(@RequestBody  Frete frete) throws URISyntaxException {
        var freteSalvo = service.salva(frete);
        return new ResponseEntity<>(freteSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Frete> atualiza(@PathVariable Integer id,
                                          @RequestBody  Frete frete) {
        var optional = service.buscaPor(id);

        if(optional.isPresent()){
            frete.setId(id);
            Frete freteAtualizado = service.salva(frete);
            return ResponseEntity.ok(freteAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("remover/{id}")
    public ResponseEntity<?> remover(@PathVariable Integer id) {
        var optional = service.buscaPor(id);

        if (optional.isPresent()) {
            service.removePelo(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}