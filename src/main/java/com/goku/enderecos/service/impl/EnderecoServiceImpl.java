package com.goku.enderecos.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goku.enderecos.builder.EnderecoBuilder;
import com.goku.enderecos.dto.EnderecoDTO;
import com.goku.enderecos.dto.NovoEnderecoDTO;
import com.goku.enderecos.function.Endereco2EnderecoDTOFunction;
import com.goku.enderecos.model.Endereco;
import com.goku.enderecos.repository.EnderecoRepository;
import com.goku.enderecos.service.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public void criarEndereco(NovoEnderecoDTO novoEnderecoDTO) {
		Endereco endereco = new EnderecoBuilder().cep(novoEnderecoDTO.getCep())
				.logradouro(novoEnderecoDTO.getLogradouro()).numero(novoEnderecoDTO.getNumero())
				.bairro(novoEnderecoDTO.getBairro()).cidade(novoEnderecoDTO.getCidade())
				.estado(novoEnderecoDTO.getEstado()).pais(novoEnderecoDTO.getPais()).build();

		enderecoRepository.save(endereco);
	}

	@Override
	public List<EnderecoDTO> listarEnderecos() {
		return StreamSupport.stream(enderecoRepository.findAll().spliterator(), false)
				.map(new Endereco2EnderecoDTOFunction()).collect(Collectors.toList());
	}

}
