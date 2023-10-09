package com.edgard.rinha;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, String> {

//    @Query(value = "select * from pessoa p where apelido ilike %?1% or nome ilike %?1% or array_to_string(stack, ',') ilike %?1% limit 50", nativeQuery = true)
//    List<Pessoa> findByTerm(String term);

    List<Pessoa> findAllBySearchableContains(String searchable);

    boolean existsByApelido(String apelido);

}
