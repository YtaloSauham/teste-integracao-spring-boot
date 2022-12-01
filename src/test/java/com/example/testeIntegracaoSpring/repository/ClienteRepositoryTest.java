package com.example.testeIntegracaoSpring.repository;

import com.example.testeIntegracaoSpring.model.Cliente;
import com.example.testeIntegracaoSpring.service.ClienteService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ClienteRepositoryTest {
    @Autowired
    private ClienteRepository repository;
    @Autowired
    private ClienteService service;

    @AfterAll
    void salvaClienteTest(){
        String nomeCliente = "João";
        Cliente cliente = Cliente.builder().nome(nomeCliente).build();
        service.salva(cliente);
    }

    @Test
    void deveBuscarClienteQueContenhamNome(){
        String nome = "J";
        var clientes = repository.findByNomeContaining(nome);

        clientes.forEach(cliente -> {
            assertTrue(cliente.getNome().contains(nome));
        });
    }

    @Test
    void deveBuscarClientePorId(){
        int expectedId = 1;
        var cliente = repository.findByFretes_Id(expectedId);

        var frete = (cliente.getFretes().stream()
                .filter(fretes -> fretes.getId() == expectedId)
                .collect(Collectors.toList())
        );

        assertEquals(expectedId, frete.get(0).getId());
    }
}