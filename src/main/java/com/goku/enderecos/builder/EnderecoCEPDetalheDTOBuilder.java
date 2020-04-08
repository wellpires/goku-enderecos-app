package com.goku.enderecos.builder;

import com.goku.enderecos.dto.EnderecoCEPDetalheDTO;

public class EnderecoCEPDetalheDTOBuilder {

	private Long cep;
	private String logradouro;
	private Long numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;

	public EnderecoCEPDetalheDTOBuilder cep(Long cep) {
		this.cep = cep;
		return this;
	}

	public EnderecoCEPDetalheDTOBuilder logradouro(String logradouro) {
		this.logradouro = logradouro;
		return this;
	}

	public EnderecoCEPDetalheDTOBuilder numero(Long numero) {
		this.numero = numero;
		return this;
	}

	public EnderecoCEPDetalheDTOBuilder bairro(String bairro) {
		this.bairro = bairro;
		return this;
	}

	public EnderecoCEPDetalheDTOBuilder cidade(String cidade) {
		this.cidade = cidade;
		return this;
	}

	public EnderecoCEPDetalheDTOBuilder estado(String estado) {
		this.estado = estado;
		return this;
	}

	public EnderecoCEPDetalheDTOBuilder pais(String pais) {
		this.pais = pais;
		return this;
	}

	public EnderecoCEPDetalheDTO build() {
		EnderecoCEPDetalheDTO endereco = new EnderecoCEPDetalheDTO();
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
