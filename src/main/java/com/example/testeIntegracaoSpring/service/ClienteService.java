package com.example.testeIntegracaoSpring.service;

import com.example.testeIntegracaoSpring.model.Cliente;
import com.example.testeIntegracaoSpring.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public List<Cliente> todos() {
        return repository.findAll();
    }

    public Optional<Cliente> buscaPor(Integer id) {
        return repository.findById(id);
    }

    public List<Cliente> buscaPor(String nome) {
        return repository.findByNomeContaining(nome);
    }

    public Cliente buscarPorFretes_Id(Integer id) {
        return repository.findByFretes_Id(id);
    }

    @Transactional
    public Cliente salva(Cliente cliente) {
        return repository.save(cliente);
    }


    @Transactional
    public void removePelo(Integer id) {
        repository.deleteById(id);
    }
}