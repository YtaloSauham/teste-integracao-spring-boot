package com.example.testeIntegracaoSpring.controller;

import com.example.testeIntegracaoSpring.model.Cidade;
import com.example.testeIntegracaoSpring.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    @Autowired
    private CidadeService service;

    @GetMapping("/")
    public ResponseEntity<List<Cidade>> todos() {
        var cidades = service.todos();

        if(cidades.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cidades);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Cidade> buscaPor(@PathVariable Integer id) {
        var optional = service.buscaPor(id);

        if(optional.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<List<Cidade>> buscaPor(@PathVariable String nome) {
        var cidades = service.buscaPor(nome);

        if(cidades.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cidades);
    }

    @GetMapping("uf/{uf}")
    public ResponseEntity<List<Cidade>> buscaPorUf(@PathVariable String uf) {
        var cidades = service.buscarPorUf(uf);

        if(cidades.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cidades);
    }

    @GetMapping("/frete/{id}")
    public ResponseEntity<Cidade> buscarPorFrete_id(@PathVariable Integer id){
        var optional = service.buscarPorFrete_id(id);

        if(optional == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(optional);
    }

    @PostMapping("/inserir")
    public ResponseEntity<Cidade> salva(@RequestBody  Cidade cidade) throws URISyntaxException {
        var cidadeSalva = service.salva(cidade);
        return new ResponseEntity<>(cidadeSalva, HttpStatus.CREATED);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        Optional<Cidade> optional = service.buscaPor(id);
        if(optional.isPresent()){
            service.removePelo(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}