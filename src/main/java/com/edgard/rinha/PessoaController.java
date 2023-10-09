package com.edgard.rinha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class PessoaController {

    @Autowired
    private PessoaRepository repository;

    @PostMapping("/pessoas")
    public ResponseEntity create(@RequestBody PessoaDTO pessoa){
        if(pessoa.getNome() == null || pessoa.getNome().length() > 32) {
            return ResponseEntity.badRequest().build();
        }
        if(pessoa.getApelido() == null || pessoa.getApelido().length() > 32) {
            return ResponseEntity.badRequest().build();
        }


        if(pessoa.getNascimento() == null) {
            return ResponseEntity.badRequest().build();
        }

        Date date;
        try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(pessoa.getNascimento());
        }catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }

        if(repository.existsByApelido(pessoa.getApelido())) {
            return ResponseEntity.unprocessableEntity().build();
        }

        UUID uuid = UUID.randomUUID();


        Pessoa p = new Pessoa();
        p.setApelido(pessoa.getApelido());
        p.setNome(pessoa.getNome());
        p.setId(uuid.toString());
        p.setNascimento(date);
        p.setStack(pessoa.getStack());

        List<String> stack = Optional.ofNullable(pessoa.getStack()).orElse(Collections.EMPTY_LIST);
        String stackAsString = stack.stream().collect(Collectors.joining(","));
        p.setSearchable(stackAsString + "," + pessoa.getNome() + "," + pessoa.getApelido());

        repository.save(p);

        return ResponseEntity.created(URI.create("/pessoas/" + uuid)).build();
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> getPessoa(@PathVariable String id) {
        Optional<Pessoa> pessoaOptional = repository.findById(id);
        return pessoaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/pessoas")
    public List<Pessoa> getPessoas(@RequestParam String t) {
        return repository.findAllBySearchableContains(t);
    }

    @GetMapping("/contagem-pessoas")
    public long count() {
        return repository.count();
    }



}
