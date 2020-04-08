package com.goku.enderecos.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.goku.enderecos.builder.EnderecoBuilder;
import com.goku.enderecos.dto.EditarEnderecoDTO;
import com.goku.enderecos.dto.EnderecoCEPDetalheDTO;
import com.goku.enderecos.dto.EnderecoDTO;
import com.goku.enderecos.dto.NovoEnderecoDTO;
import com.goku.enderecos.exception.EnderecoDuplicadoException;
import com.goku.enderecos.exception.EnderecoNotFoundException;
import com.goku.enderecos.function.Endereco2EnderecoCEPDetalheDTOFunction;
import com.goku.enderecos.function.Endereco2EnderecoDTOFunction;
import com.goku.enderecos.model.Endereco;
import com.goku.enderecos.repository.EnderecoRepository;
import com.goku.enderecos.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	@CacheEvict(cacheNames = { "enderecos-cache", "enderecos-cep-cache" }, allEntries = true)
	public void criarEndereco(NovoEnderecoDTO novoEnderecoDTO) {
		
		if(enderecoRepository.findByCep(novoEnderecoDTO.getCep()).isPresent()) {
			throw new EnderecoDuplicadoException();
		}
		
		Endereco endereco = new EnderecoBuilder().cep(novoEnderecoDTO.getCep())
				.logradouro(novoEnderecoDTO.getLogradouro()).numero(novoEnderecoDTO.getNumero())
				.bairro(novoEnderecoDTO.getBairro()).cidade(novoEnderecoDTO.getCidade())
				.estado(novoEnderecoDTO.getEstado()).pais(novoEnderecoDTO.getPais()).build();

		enderecoRepository.save(endereco);
	}

	@Override
	public List<EnderecoDTO> listarEnderecos() {
		return enderecoRepository.findAll().stream().map(new Endereco2EnderecoDTOFunction())
				.collect(Collectors.toList());
	}

	@Override
	@CachePut(cacheNames = { "enderecos-cep-cache" })
	@CacheEvict(value = "enderecos-cache", allEntries = true)
	public void editarEndereco(Long cep, EditarEnderecoDTO editarEnderecoDTO) {

		Endereco endereco = enderecoRepository.findByCep(cep).orElseThrow(EnderecoNotFoundException::new);
		Endereco enderecoChanged = new EnderecoBuilder(endereco).modify(editarEnderecoDTO);

		enderecoRepository.save(enderecoChanged);

	}

	@Override
	public EnderecoCEPDetalheDTO buscarPorCEP(Long cep) {
		return enderecoRepository.findByCep(cep).map(new Endereco2EnderecoCEPDetalheDTOFunction())
				.orElseThrow(EnderecoNotFoundException::new);
	}

	@Override
	@CachePut(cacheNames = { "enderecos-cep-cache" })
	@CacheEvict(value = "enderecos-cache", allEntries = true)
	public void deletarEndereco(Long cep) {
		Endereco endereco = enderecoRepository.findByCep(cep).orElseThrow(EnderecoNotFoundException::new);
		enderecoRepository.deleteById(endereco.getId());
	}

}
