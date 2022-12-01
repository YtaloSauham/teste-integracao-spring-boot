package com.example.testeIntegracaoSpring.service;

import com.example.testeIntegracaoSpring.model.Cidade;
import com.example.testeIntegracaoSpring.repository.CidadeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {
    @Autowired
    private CidadeRepository repository;

    public List<Cidade> todos() {
        return repository.findAll();
    }

    public Optional<Cidade> buscaPor(Integer id) {
        return repository.findById(id);
    }

    public List<Cidade> buscaPor(String nome) {
        return repository.findByNomeContaining(nome);
    }

    public List<Cidade> buscarPorUf(String uf) {
        return repository.findByUf(uf);
    }

    public  Cidade buscarPorFrete_id(Integer id) {
        return repository.findByFretesId(id);
    }

    @Transactional
    public Cidade salva(Cidade cliente) {
        return repository.save(cliente);
    }

    @Transactional
    public void removePelo(Integer id) {
        repository.deleteById(id);
    }
}