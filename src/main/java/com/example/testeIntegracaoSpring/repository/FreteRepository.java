package com.example.testeIntegracaoSpring.repository;

import com.example.testeIntegracaoSpring.model.Frete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Integer> {
    @Override
    List<Frete> findAll();

    @Override
    Optional<Frete> findById(Integer integer);

    List<Frete> findByClienteId(Integer id);

    List<Frete> findByClienteIdOrderByValor(Integer id);

    List<Frete> findByCidadeId(Integer id);
}