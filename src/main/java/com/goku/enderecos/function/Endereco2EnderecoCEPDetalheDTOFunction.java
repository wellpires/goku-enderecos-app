package com.goku.enderecos.function;

import java.util.function.Function;

import com.goku.enderecos.dto.EnderecoCEPDetalheDTO;
import com.goku.enderecos.model.Endereco;

public class Endereco2EnderecoCEPDetalheDTOFunction implements Function<Endereco, EnderecoCEPDetalheDTO> {

	@Override
	public EnderecoCEPDetalheDTO apply(Endereco endereco) {
		EnderecoCEPDetalheDTO enderecoCEPDetalheDTO = new EnderecoCEPDetalheDTO();
		enderecoCEPDetalheDTO.setBairro(endereco.getBairro());
		enderecoCEPDetalheDTO.setCep(endereco.getCep());
		enderecoCEPDetalheDTO.setCidade(endereco.getCidade());
		enderecoCEPDetalheDTO.setEstado(endereco.getEstado());
		enderecoCEPDetalheDTO.setLogradouro(endereco.getLogradouro());
		enderecoCEPDetalheDTO.setNumero(endereco.getNumero());
		enderecoCEPDetalheDTO.setPais(endereco.getPais());
		return enderecoCEPDetalheDTO;
	}

}
