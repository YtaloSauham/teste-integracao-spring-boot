package com.example.testeIntegracaoSpring.controller;


import com.example.testeIntegracaoSpring.model.Cliente;
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
class ClienteControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void deveListarTodosClientes() {
        ParameterizedTypeReference<List<Cliente>> tipoRetorno = new ParameterizedTypeReference<List<Cliente>>() {};

        ResponseEntity<List<Cliente>> response = testRestTemplate.exchange(
                "/clientes/", HttpMethod.GET, null, tipoRetorno
        );
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveBuscaClientePorId() {
        int expectedId = 4;
        ResponseEntity<Cliente> response = testRestTemplate.exchange(
                "/clientes/id/{id}", HttpMethod.GET, null, Cliente.class, expectedId
        );
        System.out.println("######## " + response.getBody());
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveBuscarClientePorNome() {
        ParameterizedTypeReference<List<Cliente>> tipoRetorno = new ParameterizedTypeReference<List<Cliente>>() {};

        String expectedName = "L";
        ResponseEntity<List<Cliente>> response = testRestTemplate.exchange(
                "/clientes/nome/{nome}", HttpMethod.GET, null, tipoRetorno,expectedName
        );
        System.out.println("######## " + response.getBody());
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test // RETURN NOT_FOUND
    void deveBuscarClientePorFretes_Id() {
        int expectedId = 10;
        ResponseEntity<Cliente> response = testRestTemplate.exchange(
                "/clientes/fretes/{id}", HttpMethod.GET, null, Cliente.class, expectedId
        );
        System.out.println("######## " + response.getBody());
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveSalvarCliente() {
        Cliente cliente = Cliente.builder().nome("Nulozinho").telefone("98988525278").build();
        HttpEntity<Cliente> httpEntity = new HttpEntity<>(cliente);

        ResponseEntity<Cliente> response = testRestTemplate.exchange(
                "/clientes/inserir/", HttpMethod.POST, httpEntity, Cliente.class
        );
        System.out.println("######## " + response.getStatusCode());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


    @Test
    void deveRemoverClientePorId() {
        int expectedId = 1;
        ResponseEntity<?> response = testRestTemplate.exchange(
                "/clientes/remover/{id}", HttpMethod.DELETE, null, Cliente.class, expectedId
        );
        assertEquals(HttpStatus.NO_CONTENT,response.getStatusCode());
    }
}