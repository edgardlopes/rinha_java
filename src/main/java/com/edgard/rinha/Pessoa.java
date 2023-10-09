package com.edgard.rinha;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"apelido"})
        }
)
public class Pessoa {

    @Id
    private String id;

    @Column(length = 32, nullable = false)
    private String apelido;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false)
    private Date nascimento;

    @Column
    private List<String> stack;

    private @Column String searchable;


    public void setSearchable(String searchable) {
        this.searchable = searchable;
    }

    public String getSearchable() {
        return searchable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public List<String> getStack() {
        return stack;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }
}
