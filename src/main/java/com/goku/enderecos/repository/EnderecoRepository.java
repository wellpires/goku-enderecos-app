package com.goku.enderecos.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.goku.enderecos.model.Endereco;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

	Optional<Endereco> findByCep(Long cep);

}
