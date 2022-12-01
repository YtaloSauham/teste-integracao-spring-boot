package com.example.testeIntegracaoSpring.controller;

import com.example.testeIntegracaoSpring.model.Cliente;
import com.example.testeIntegracaoSpring.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping("/")
    public ResponseEntity<Cliente> listarTodos(){
        var optional = service.todos();

        if (!optional.isEmpty()){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Cliente> buscaPor(@PathVariable Integer id) {
        var optional = service.buscaPor(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Cliente> buscaPor(@PathVariable String nome) {
        var optional = service.buscaPor(nome);

        if (!optional.isEmpty()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/frete/{id}")
    public ResponseEntity<Cliente> buscarPorFretes_Id(@PathVariable Integer id) {
        var optional = service.buscarPorFretes_Id(id);

        if (optional != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/inserir")
    public ResponseEntity<Cliente> cadastro(@RequestBody Cliente cliente) throws URISyntaxException {
        var clienteSalvo = service.salva(cliente);
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }

    @DeleteMapping("remover/{id}")
    public ResponseEntity<?> remover(@PathVariable Integer id) {
        Optional<Cliente> optional = service.buscaPor(id );

        if (optional.isPresent()) {
            service.removePelo(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}