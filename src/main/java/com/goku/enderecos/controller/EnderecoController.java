package com.goku.enderecos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goku.enderecos.controller.resource.EnderecoResource;
import com.goku.enderecos.dto.EditarEnderecoDTO;
import com.goku.enderecos.dto.EnderecoCEPDetalheDTO;
import com.goku.enderecos.dto.EnderecoDTO;
import com.goku.enderecos.dto.EnderecosDTO;
import com.goku.enderecos.dto.NovoEnderecoDTO;
import com.goku.enderecos.service.EnderecoService;

@RestController
@RequestMapping("endereco")
public class EnderecoController implements EnderecoResource {

	@Autowired
	private EnderecoService enderecoService;

	@Override
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> criarEndereco(@RequestBody NovoEnderecoDTO novoEndereco) {
		enderecoService.criarEndereco(novoEndereco);
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EnderecosDTO> listarEnderecos() {
		List<EnderecoDTO> enderecos = enderecoService.listarEnderecos();
		return ResponseEntity.ok(new EnderecosDTO(enderecos));
	}

	@Override
	@PutMapping(path = "/{cep}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> editarEndereco(@PathVariable("cep") Long cep,
			@RequestBody EditarEnderecoDTO editarEnderecoDTO) {

		enderecoService.editarEndereco(cep, editarEnderecoDTO);

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<EnderecoCEPDetalheDTO> buscarPorCEP(Long cep) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> deletarEndereco(Long cep) {
		// TODO Auto-generated method stub
		return null;
	}

}
