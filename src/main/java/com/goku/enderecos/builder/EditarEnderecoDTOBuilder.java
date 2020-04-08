package com.goku.enderecos.builder;

import com.goku.enderecos.dto.EditarEnderecoDTO;

public class EditarEnderecoDTOBuilder {

	private String bairro;
	private String cidade;
	private Long numero;

	public EditarEnderecoDTOBuilder bairro(String bairro) {
		this.bairro = bairro;
		return this;
	}

	public EditarEnderecoDTOBuilder cidade(String cidade) {
		this.cidade = cidade;
		return this;
	}

	public EditarEnderecoDTOBuilder numero(Long numero) {
		this.numero = numero;
		return this;
	}

	public EditarEnderecoDTO build() {
		EditarEnderecoDTO editarEnderecoDTO = new EditarEnderecoDTO();
		editarEnderecoDTO.setBairro(bairro);
		editarEnderecoDTO.setCidade(cidade);
		editarEnderecoDTO.setNumero(numero);
		return editarEnderecoDTO;
	}

}
