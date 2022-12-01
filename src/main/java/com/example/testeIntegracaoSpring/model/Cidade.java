package com.example.testeIntegracaoSpring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_cidade")
    private Integer id;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cidade")
    private List<Frete> fretes;


    private String nome;


    private String uf;


    private float taxa;
}