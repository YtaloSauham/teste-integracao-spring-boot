package com.example.testeIntegracaoSpring.service;

import java.util.List;
import java.util.Optional;



import com.example.testeIntegracaoSpring.model.Frete;
import com.example.testeIntegracaoSpring.repository.FreteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FreteService {
    @Autowired
    private FreteRepository repository;

    public List<Frete> todos() {
        return repository.findAll();
    }

    public Optional<Frete> buscaPor(Integer id) {
        return repository.findById(id);
    }

    public List<Frete> buscaPorCliente_Id(Integer id) {
        return repository.findByClienteId(id);
    }

    public List<Frete> buscaPorCidade_Id(Integer id) {
        return repository.findByCidadeId(id);
    }

    public List<Frete> buscarPorCliente_IdOrderByValcrAsc(Integer id) {
        return repository.findByClienteIdOrderByValor(id);
    }

    @Transactional
    public Frete salva(Frete frete) {
        return repository.save(frete);
    }

    @Transactional
    public void removePelo(Integer id) {
        repository.deleteById(id);
    }
}
