package com.goku.enderecos.builder;

import java.util.ArrayList;
import java.util.List;

import com.goku.enderecos.dto.EnderecoDTO;

public class EnderecoDTOBuilder {

	private Long cep;
	private String logradouro;
	private int quantidadeItens;

	public EnderecoDTOBuilder cep(Long cep) {
		this.cep = cep;
		return this;
	}

	public EnderecoDTOBuilder logradouro(String logradouro) {
		this.logradouro = logradouro;
		return this;
	}

	public EnderecoDTO build() {
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		enderecoDTO.setCep(cep);
		enderecoDTO.setLogradouro(logradouro);
		return enderecoDTO;
	}

	public EnderecoDTOBuilder quantidadeItens(int quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
		return this;
	}

	public List<EnderecoDTO> buildList() {

		List<EnderecoDTO> enderecos = new ArrayList<>();
		for (Integer i = 0; i < quantidadeItens; i++) {
			EnderecoDTO enderecoDTO = new EnderecoDTO();
			enderecoDTO.setCep(i.longValue());
			enderecoDTO.setLogradouro("Logradouro ".concat(i.toString()));
			enderecos.add(enderecoDTO);
		}

		return enderecos;
	}

}
