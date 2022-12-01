package com.example.testeIntegracaoSpring.controller;

import com.example.testeIntegracaoSpring.model.Cidade;
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
class CidadeControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void deveListarTodasCidades() {
        ParameterizedTypeReference<List<Cidade>> tipoRetorno = new ParameterizedTypeReference<List<Cidade>>() {};

        ResponseEntity<List<Cidade>> response = testRestTemplate.exchange(
                "/cidades/", HttpMethod.GET,null, tipoRetorno
        );
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveBuscarCidadePorId() {
        int expectedId = 27;
        ResponseEntity<Cidade> response = testRestTemplate.exchange(
                "/cidades/id/{id}",HttpMethod.GET,null, Cidade.class, expectedId
        );
        System.out.println("######## " + response.getBody());
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveBuscarCidadePorNome() {
        ParameterizedTypeReference<List<Cidade>> tipoRetorno = new ParameterizedTypeReference<>() {};

        String expectedName = "Recife";
        ResponseEntity<List<Cidade>> response = testRestTemplate.exchange(
                "/cidades/nome/{nome}",HttpMethod.GET, null, tipoRetorno,expectedName
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveBuscarCidadePorUf() {
        ParameterizedTypeReference<List<Cidade>> tipoRetorno = new ParameterizedTypeReference<List<Cidade>>() {};

        String expectedUf = "MA";
        ResponseEntity<List<Cidade>> response = testRestTemplate.exchange(
                "/cidades/uf/{uf}",HttpMethod.GET, null, tipoRetorno,expectedUf
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveBuscarCidadePorFrete_id() {
        int expectedId = 1;
        ResponseEntity<Cidade> response = testRestTemplate.exchange(
                "/cidades/frete/{id}",HttpMethod.GET,null, Cidade.class, expectedId
        );
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveSalvarCidade(){
        Cidade cidade = Cidade.builder().nome("Null City").taxa(10).build();
        HttpEntity<Cidade> httpEntity = new HttpEntity<>(cidade);

        ResponseEntity<Cidade> response = testRestTemplate.exchange(
                "/cidades/inserir/", HttpMethod.POST, httpEntity, Cidade.class
        );
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void deveRemoverCidadePorId(){
        int expectedId = 1;
        ResponseEntity<?> response = testRestTemplate.exchange(
                "/cidades/remover/{id}", HttpMethod.DELETE, null, Cidade.class, expectedId
        );
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }
}
