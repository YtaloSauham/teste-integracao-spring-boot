package com.example.testeIntegracaoSpring.controller;

import com.example.testeIntegracaoSpring.model.Cidade;
import com.example.testeIntegracaoSpring.model.Frete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FreteControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void deveListarTodosFretes() {
        ParameterizedTypeReference<List<Frete>> tipoRetorno = new ParameterizedTypeReference<List<Frete>>() {};
        ResponseEntity<List<Frete>> response = testRestTemplate.exchange(
                "/fretes/", HttpMethod.GET,null, tipoRetorno
        );
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveBuscarFretePorId() {
        int expectedId = 2;
        ResponseEntity<Frete> response = testRestTemplate.exchange(
                "/fretes/id/{id}", HttpMethod.GET,null, Frete.class, expectedId
        );
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveBuscarFretePorCliente_Id() {
        int expectedId = 2;
        ParameterizedTypeReference<List<Frete>> tipoRetorno = new ParameterizedTypeReference<List<Frete>>() {};
        ResponseEntity<List<Frete>> response = testRestTemplate.exchange(
                "/fretes/cliente/{id}", HttpMethod.GET,null, tipoRetorno, expectedId
        );
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test // ERROR get.body return NULL
    void deveBuscarFretePorCidade_Id() {
        int expectedId = 5;
        ParameterizedTypeReference<List<Frete>> tipoRetorno = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<Frete>> response = testRestTemplate.exchange(
                "/fretes/cidade/{id}", HttpMethod.GET,null, tipoRetorno, expectedId
        );
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test // ERROR get.body return NULL
    void deveBuscarFretePorCliente_IdOrderByValcrAsc() {
        int expectedId = 10;
        ParameterizedTypeReference<List<Frete>> tipoRetorno = new ParameterizedTypeReference<List<Frete>>() {};
        ResponseEntity<List<Frete>> response = testRestTemplate.exchange(
                "/fretes/cliente/order/{id}", HttpMethod.GET,null, tipoRetorno, expectedId
        );
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveSalvarFrete() {
        Frete frete = Frete.builder().valor(10).peso(50).descricao("caneta").build();
        HttpEntity<Frete> httpEntity = new HttpEntity<>(frete);

        ResponseEntity<Frete> response = testRestTemplate.exchange(
                "/fretes/inserir/", HttpMethod.POST, httpEntity, Frete.class
        );
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void remover() {
        int expectedId = 1;
        ResponseEntity<?> response = testRestTemplate.exchange(
                "/fretes/remover/{id}", HttpMethod.DELETE, null, Cidade.class, expectedId
        );
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }
}