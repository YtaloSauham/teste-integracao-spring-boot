package com.example.testeIntegracaoSpring.repository;


import com.example.testeIntegracaoSpring.model.Cidade;
import com.example.testeIntegracaoSpring.service.CidadeService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CidadeRepositoryTest {

    @Autowired
    private CidadeRepository cidadeRepository;
    private CidadeService service;


    @AfterAll
    void salvarCidaes(){
        String nomeCidade = "Texas";
        Cidade cidadeSalvar = Cidade.builder().nome(nomeCidade).taxa(13).build();
        service.salva(cidadeSalvar);
    }

    @Test
    void deveBuscarCidadesQueContenhamNome(){

        String nome = "texas";
        var cidades = cidadeRepository.findByNomeContaining(nome);

        cidades.forEach(cidade -> {
            assertTrue(cidade.getNome().contains(nome));
        });
    }

    @Test
    void deveBuscarCidadePorFretesId(){
        int expectedId = 1;
        var cidade = cidadeRepository.findByFretesId(expectedId);

        var frete = (cidade.getFretes().stream()
                .filter(fretes -> fretes.getId() == expectedId)
                .collect(Collectors.toList())
        );

        assertEquals(expectedId, frete.get(0).getId());
    }

    @Test
    void deveBuscarCidadesPorUf(){
        String uf = "MA";
        var cidades = cidadeRepository.findByUf(uf);

        cidades.forEach(cidade -> {
            assertTrue(cidade.getUf().equals(uf));
        });
    }

}