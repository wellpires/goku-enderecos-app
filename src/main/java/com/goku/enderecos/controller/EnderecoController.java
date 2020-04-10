package com.goku.enderecos.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.goku.enderecos.dto.NovoEnderecoDTO;
import com.goku.enderecos.response.EnderecoCEPDetalheResponse;
import com.goku.enderecos.response.EnderecosResponse;
import com.goku.enderecos.service.EnderecoService;

@RestController
@RequestMapping("/api/v1/enderecos")
public class EnderecoController implements EnderecoResource {

	@Autowired
	private EnderecoService enderecoService;

	@Override
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> criarEndereco(@Valid @RequestBody NovoEnderecoDTO novoEndereco) {
		enderecoService.criarEndereco(novoEndereco);
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EnderecosResponse> listarEnderecos() {
		List<EnderecoDTO> enderecos = enderecoService.listarEnderecos();
		return ResponseEntity.ok(new EnderecosResponse(enderecos));
	}

	@Override
	@PutMapping(path = "/{cep}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> editarEndereco(@PathVariable("cep") Long cep,
			@Valid @RequestBody EditarEnderecoDTO editarEnderecoDTO) {

		enderecoService.editarEndereco(cep, editarEnderecoDTO);

		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping(path = "/{cep}")
	public ResponseEntity<EnderecoCEPDetalheResponse> buscarPorCEP(@PathVariable("cep") Long cep) {
		EnderecoCEPDetalheDTO enderecoCEPDetalhe = enderecoService.buscarPorCEP(cep);
		return ResponseEntity.ok(new EnderecoCEPDetalheResponse(enderecoCEPDetalhe));
	}

	@Override
	@DeleteMapping(path = "/{cep}")
	public ResponseEntity<Void> deletarEndereco(@PathVariable("cep") Long cep) {
		enderecoService.deletarEndereco(cep);
		return ResponseEntity.noContent().build();
	}

}
