package com.goku.enderecos.builder;

import com.goku.enderecos.dto.EnderecoDTO;

public class EnderecoDTOBuilder {

	private Long cep;
	private String logradouro;

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

}
