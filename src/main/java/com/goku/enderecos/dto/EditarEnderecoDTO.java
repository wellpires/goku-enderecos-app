package com.goku.enderecos.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import com.goku.enderecos.annotation.EditarEndereco;

@EditarEndereco(message = "{form.obrigatorio.editar-endereco}")
public class EditarEnderecoDTO {

	private String logradouro;

	@DecimalMin(inclusive = true, value = "1", message = "{campo.invalido.numero_minimo}")
	@DecimalMax(inclusive = true, value = "99999", message = "{campo.invalido.numero_maximo}")
	private Long numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;

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
