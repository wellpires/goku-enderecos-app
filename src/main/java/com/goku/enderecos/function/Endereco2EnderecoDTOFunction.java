package com.goku.enderecos.function;

import java.util.function.Function;

import com.goku.enderecos.builder.EnderecoDTOBuilder;
import com.goku.enderecos.dto.EnderecoDTO;
import com.goku.enderecos.model.Endereco;

public class Endereco2EnderecoDTOFunction implements Function<Endereco, EnderecoDTO> {

	@Override
	public EnderecoDTO apply(Endereco endereco) {
		return new EnderecoDTOBuilder().cep(endereco.getCep()).logradouro(endereco.getLogradouro()).build();
	}

}
