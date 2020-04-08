package com.goku.enderecos.builder;

import com.goku.enderecos.dto.NovoEnderecoDTO;

public class NovoEnderecoDTOBuilder {

	private Long cep;
	private String logradouro;
	private Long numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;

	public NovoEnderecoDTOBuilder cep(Long cep) {
		this.cep = cep;
		return this;
	}

	public NovoEnderecoDTOBuilder logradouro(String logradouro) {
		this.logradouro = logradouro;
		return this;
	}

	public NovoEnderecoDTOBuilder numero(Long numero) {
		this.numero = numero;
		return this;
	}

	public NovoEnderecoDTOBuilder bairro(String bairro) {
		this.bairro = bairro;
		return this;
	}

	public NovoEnderecoDTOBuilder cidade(String cidade) {
		this.cidade = cidade;
		return this;
	}

	public NovoEnderecoDTOBuilder estado(String estado) {
		this.estado = estado;
		return this;
	}

	public NovoEnderecoDTOBuilder pais(String pais) {
		this.pais = pais;
		return this;
	}

	public NovoEnderecoDTO build() {
		NovoEnderecoDTO endereco = new NovoEnderecoDTO();
		endereco.setCep(cep);
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		endereco.setEstado(estado);
		endereco.setPais(pais);

		return endereco;
	}

}
