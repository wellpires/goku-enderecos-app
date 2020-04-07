package com.goku.enderecos.service;

import java.util.List;

import com.goku.enderecos.dto.EnderecoDTO;
import com.goku.enderecos.dto.NovoEnderecoDTO;

public interface EnderecoService {

	public void criarEndereco(NovoEnderecoDTO novoEnderecoDTO);

	public List<EnderecoDTO> listarEnderecos();

}
