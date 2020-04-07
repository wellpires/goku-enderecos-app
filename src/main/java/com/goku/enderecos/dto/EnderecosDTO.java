package com.goku.enderecos.dto;

import java.util.List;

public class EnderecosDTO {

	private List<EnderecoDTO> enderecos;

	public EnderecosDTO(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}

	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}

}
