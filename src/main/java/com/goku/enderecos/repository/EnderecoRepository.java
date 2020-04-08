package com.goku.enderecos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.goku.enderecos.model.Endereco;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

	@Cacheable("enderecos-cep-cache")
	Optional<Endereco> findByCep(Long cep);

	@Cacheable("enderecos-cache")
	List<Endereco> findAll();

}
