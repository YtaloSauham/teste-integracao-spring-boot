package com.example.testeIntegracaoSpring.service;


import com.example.testeIntegracaoSpring.model.Cidade;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
public class CidadeServiceTest {

    @Autowired
    private CidadeService service;

    @Test
    void deveSalvarCidade(){
        String nomeCidade = "Texas";
        Cidade cidade = Cidade.builder().nome(nomeCidade).taxa(13).build();
        service.salva(cidade);

        var cidades = service.buscaPor(nomeCidade);
        cidades.forEach(city -> {
            assertEquals(nomeCidade, city.getNome());
        });
    }

    @Test
    void deveRemoverCidade(){
        int idCidadeRemovida = 1;
        service.removePelo(idCidadeRemovida);

        var cidade = service.buscaPor(idCidadeRemovida);
        assertTrue(cidade.isEmpty());
    }

}