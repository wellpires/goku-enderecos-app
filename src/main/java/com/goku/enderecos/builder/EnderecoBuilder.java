package com.goku.enderecos.builder;

import java.util.Objects;

import com.goku.enderecos.dto.EditarEnderecoDTO;
import com.goku.enderecos.model.Endereco;

public class EnderecoBuilder {

	private Long cep;
	private String logradouro;
	private Long numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private Endereco endereco;

	public EnderecoBuilder(Endereco endereco) {
		this.endereco = endereco;
	}

	public EnderecoBuilder() {
	}

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

	public Endereco modify(EditarEnderecoDTO editarEnderecoDTO) {

		Objects.requireNonNull(endereco);

		if (Objects.nonNull(editarEnderecoDTO.getCep())) {
			endereco.setCep(editarEnderecoDTO.getCep());
		}

		if (Objects.nonNull(editarEnderecoDTO.getLogradouro())) {
			endereco.setLogradouro(editarEnderecoDTO.getLogradouro());
		}

		if (Objects.nonNull(editarEnderecoDTO.getNumero())) {
			endereco.setNumero(editarEnderecoDTO.getNumero());
		}

		if (Objects.nonNull(editarEnderecoDTO.getBairro())) {
			endereco.setBairro(editarEnderecoDTO.getBairro());
		}

		if (Objects.nonNull(editarEnderecoDTO.getCidade())) {
			endereco.setCidade(editarEnderecoDTO.getCidade());
		}

		if (Objects.nonNull(editarEnderecoDTO.getEstado())) {
			endereco.setEstado(editarEnderecoDTO.getEstado());
		}

		if (Objects.nonNull(editarEnderecoDTO.getPais())) {
			endereco.setPais(editarEnderecoDTO.getPais());
		}

		return endereco;
	}

}
