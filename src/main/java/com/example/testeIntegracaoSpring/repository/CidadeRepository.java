package com.example.testeIntegracaoSpring.repository;

import com.example.testeIntegracaoSpring.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    List<Cidade> findByNomeContaining(@Param("nome") String nome);


    Cidade findByFretesId(@Param("id") Integer id);

    List<Cidade> findByUf(String uf);

}