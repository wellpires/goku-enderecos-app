package com.goku.enderecos.dto;

import java.io.Serializable;

public class EnderecoDTO implements Serializable {

	private static final long serialVersionUID = 9124119125508141443L;

	private Long cep;
	private String logradouro;

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

}
