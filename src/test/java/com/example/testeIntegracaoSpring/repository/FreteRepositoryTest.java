package com.example.testeIntegracaoSpring.repository;

import com.example.testeIntegracaoSpring.model.Frete;
import com.example.testeIntegracaoSpring.service.FreteService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
public class FreteRepositoryTest {
    @Autowired
    private FreteRepository repository;

    @Autowired
    private FreteService service;

    @AfterAll
    void salvaFrete(){
        Frete frete = Frete.builder().peso(10).valor(10).build();
        service.salva(frete);
    }

    @Test
    void deveMostrarTodosFretes(){
        var frete = repository.findAll();

        assertFalse(frete.isEmpty());
    }

    @Test
    void deveBuscarFretePorId(){
        int expectedId = 1;
        var frete = repository.findById(expectedId);

        assertEquals(expectedId, frete.get().getId());
    }

    @Test
    void deveBuscarFretePorClienteId(){
        int expectedId = 1;
        var frete = repository.findByClienteId(expectedId);

        frete.forEach(fretes -> {
            assertEquals(expectedId, fretes.getCliente().getId());
        });
    }

    @Test
    void deveBuscarFretePorClienteIdOrdenadoPorValor(){
        int expectedId = 1;
        var frete = repository.findByClienteIdOrderByValor(expectedId);

        frete.forEach(fretes -> {
            assertEquals(expectedId, fretes.getCliente().getId());
        });
    }

    @Test
    void deveBuscarFretePorCidadeId() {
        int expectedId = 1;
        var frete = repository.findByCidadeId(expectedId);

        frete.forEach(fretes -> {
            assertEquals(expectedId, fretes.getCidade().getId());
        });
    }
}