package com.goku.enderecos.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoEnderecoDTO {

	@NotNull(message = "{campo.obrigatorio.cep}")
	@DecimalMin(inclusive = true, value = "1000000", message = "{campo.invalido.cep_minimo}")
	@DecimalMax(inclusive = true, value = "99999999", message = "{campo.invalido.cep_maximo}")
	private Long cep;

	@NotBlank(message = "{campo.obrigatorio.logradouro}")
	private String logradouro;

	@NotNull(message = "{campo.obrigatorio.numero}")
	@DecimalMin(inclusive = true, value = "1", message = "{campo.invalido.numero_minimo}")
	@DecimalMax(inclusive = true, value = "99999", message = "{campo.invalido.numero_maximo}")
	private Long numero;

	@NotBlank(message = "{campo.obrigatorio.bairro}")
	private String bairro;

	@NotBlank(message = "{campo.obrigatorio.cidade}")
	private String cidade;

	@NotBlank(message = "{campo.obrigatorio.estado}")
	private String estado;

	@NotBlank(message = "{campo.obrigatorio.pais}")
	private String pais;

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

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
