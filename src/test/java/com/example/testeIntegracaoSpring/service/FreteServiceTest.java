package com.example.testeIntegracaoSpring.service;

import com.example.testeIntegracaoSpring.model.Frete;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
public class FreteServiceTest {
    @Autowired
    private FreteService service;


    @Test
    void deveSalvarFrete() {
        Frete frete = Frete.builder().peso(10).valor(10).build();
        service.salva(frete);

        var fretes = service.buscaPor(frete.getId());
        assertEquals(frete.getId(), fretes.get().getId());
    }

    @Test
    void deveRemoverFrete() {
        int idFreteRemovido = 1;
        service.removePelo(idFreteRemovido);

        var frete = service.buscaPor(idFreteRemovido);
        assertTrue(frete.isEmpty());
    }
}