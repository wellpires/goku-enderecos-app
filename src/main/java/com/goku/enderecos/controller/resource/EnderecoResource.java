package com.goku.enderecos.controller.resource;

import org.springframework.http.ResponseEntity;

import com.goku.enderecos.dto.EditarEnderecoDTO;
import com.goku.enderecos.dto.EnderecosDTO;
import com.goku.enderecos.dto.NovoEnderecoDTO;
import com.goku.enderecos.response.EnderecoCEPDetalheResponse;

public interface EnderecoResource {

	ResponseEntity<Void> criarEndereco(NovoEnderecoDTO novoEndereco);

	ResponseEntity<EnderecosDTO> listarEnderecos();

	ResponseEntity<Void> editarEndereco(Long cep, EditarEnderecoDTO editarEnderecoDTO);

	ResponseEntity<EnderecoCEPDetalheResponse> buscarPorCEP(Long cep);

	ResponseEntity<Void> deletarEndereco(Long cep);

}
