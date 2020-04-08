package com.goku.enderecos.controller.resource;

import org.springframework.http.ResponseEntity;

import com.goku.enderecos.dto.EditarEnderecoDTO;
import com.goku.enderecos.dto.EnderecosDTO;
import com.goku.enderecos.dto.NovoEnderecoDTO;
import com.goku.enderecos.response.EnderecoCEPDetalheResponse;
import com.goku.enderecos.response.ErrorResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Serviço que gerencia os endereços")
public interface EnderecoResource {

	@ApiOperation(value = "Criar um novo endereço")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 409, message = "Conflict", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<Void> criarEndereco(NovoEnderecoDTO novoEndereco);

	@ApiOperation(value = "Listar os endereços")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<EnderecosDTO> listarEnderecos();

	@ApiOperation(value = "Editar um endereço")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<Void> editarEndereco(Long cep, EditarEnderecoDTO editarEnderecoDTO);

	@ApiOperation(value = "Buscar um endereço por CEP")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<EnderecoCEPDetalheResponse> buscarPorCEP(Long cep);

	@ApiOperation(value = "Deletar um endereço")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class) })
	ResponseEntity<Void> deletarEndereco(Long cep);

}
