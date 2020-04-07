package com.goku.enderecos.builder;

import com.goku.enderecos.model.Endereco;

public class EnderecoBuilder {

	private Long cep;
	private String logradouro;
	private Long numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;

	public EnderecoBuilder cep(Long cep) {
		this.cep = cep;
		return this;
	}

	public EnderecoBuilder logradouro(String logradouro) {
		this.logradouro = logradouro;
		return this;
	}

	public EnderecoBuilder numero(Long numero) {
		this.numero = numero;
		return this;
	}

	public EnderecoBuilder bairro(String bairro) {
		this.bairro = bairro;
		return this;
	}

	public EnderecoBuilder cidade(String cidade) {
		this.cidade = cidade;
		return this;
	}

	public EnderecoBuilder estado(String estado) {
		this.estado = estado;
		return this;
	}

	public EnderecoBuilder pais(String pais) {
		this.pais = pais;
		return this;
	}

	public Endereco build() {
		Endereco endereco = new Endereco();
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
