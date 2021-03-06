package com.goku.enderecos.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goku.enderecos.builder.EditarEnderecoDTOBuilder;
import com.goku.enderecos.builder.EnderecoCEPDetalheDTOBuilder;
import com.goku.enderecos.builder.EnderecoDTOBuilder;
import com.goku.enderecos.builder.NovoEnderecoDTOBuilder;
import com.goku.enderecos.controller.advice.EnderecoControllerAdvice;
import com.goku.enderecos.dto.EditarEnderecoDTO;
import com.goku.enderecos.dto.EnderecoCEPDetalheDTO;
import com.goku.enderecos.dto.NovoEnderecoDTO;
import com.goku.enderecos.exception.EnderecoDuplicadoException;
import com.goku.enderecos.exception.EnderecoNotFoundException;
import com.goku.enderecos.response.EnderecoCEPDetalheResponse;
import com.goku.enderecos.response.EnderecosResponse;
import com.goku.enderecos.service.EnderecoService;

@SpringBootTest
public class EnderecoControllerTest {

	private static final String PATH_APP = "/api/v1/enderecos";
	private static final String POST_CREATE_ENDERECO = PATH_APP;
	private static final String GET_LIST_ALL_ENDERECO = PATH_APP;
	private static final String PUT_EDIT_ENDERECO = PATH_APP.concat("/{cep}");
	private static final String GET_ENDERECO_BY_CEP = PATH_APP.concat("/{cep}");
	private static final String DELETE_ENDERECO = PATH_APP.concat("/{cep}");

	@InjectMocks
	private EnderecoController enderecoController;

	@Mock
	private EnderecoService enderecoService;

	private MockMvc mockMVC;

	private ObjectMapper mapper;

	@BeforeEach
	public void setup() {

		this.mockMVC = MockMvcBuilders.standaloneSetup(enderecoController)
				.setControllerAdvice(new EnderecoControllerAdvice()).build();
		this.mapper = new ObjectMapper();

	}

	@Test
	void deveCriarEndereco() throws JsonProcessingException, Exception {

		NovoEnderecoDTO novoEndereco = new NovoEnderecoDTOBuilder().cep(25666357l).logradouro("Rua teste").numero(666l)
				.bairro("Bairro teste").cidade("Cidade teste").estado("Teste do norte").pais("Teste").build();
		MvcResult response = mockMVC.perform(post(POST_CREATE_ENDERECO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoEndereco))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.NO_CONTENT);

	}

	@Test
	void naoDeveCriarEnderecoPoisOCampoCEPNaoFoiFornecido() throws Exception {

		NovoEnderecoDTO novoEndereco = new NovoEnderecoDTOBuilder().logradouro("Rua teste").numero(666l)
				.bairro("Bairro teste").cidade("Cidade teste").estado("Teste do norte").pais("Teste").build();
		MvcResult response = mockMVC.perform(post(POST_CREATE_ENDERECO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoEndereco))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	void naoDeveCriarEnderecoPoisOCampoCEPFoiFornecidoComValorAbaixoDoMinimo() throws Exception {

		NovoEnderecoDTO novoEndereco = new NovoEnderecoDTOBuilder().cep(1l).logradouro("Rua teste").numero(666l)
				.bairro("Bairro teste").cidade("Cidade teste").estado("Teste do norte").pais("Teste").build();
		MvcResult response = mockMVC.perform(post(POST_CREATE_ENDERECO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoEndereco))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	void naoDeveCriarEnderecoPoisOCampoCEPFoiFornecidoComValorAcimaDoMaximo() throws Exception {

		NovoEnderecoDTO novoEndereco = new NovoEnderecoDTOBuilder().cep(99999999999999l).logradouro("Rua teste")
				.numero(666l).bairro("Bairro teste").cidade("Cidade teste").estado("Teste do norte").pais("Teste")
				.build();
		MvcResult response = mockMVC.perform(post(POST_CREATE_ENDERECO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoEndereco))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	void naoDeveCriarEnderecoPoisOCampoLogradouroNaoFoiFornecido() throws Exception {

		NovoEnderecoDTO novoEndereco = new NovoEnderecoDTOBuilder().cep(65888254l).numero(666l).bairro("Bairro teste")
				.cidade("Cidade teste").estado("Teste do norte").pais("Teste").build();
		MvcResult response = mockMVC.perform(post(POST_CREATE_ENDERECO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoEndereco))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	void naoDeveCriarEnderecoPoisOCampoNumeroNaoFoiFornecido() throws Exception {

		NovoEnderecoDTO novoEndereco = new NovoEnderecoDTOBuilder().cep(65888254l).logradouro("Rua teste")
				.bairro("Bairro teste").cidade("Cidade teste").estado("Teste do norte").pais("Teste").build();
		MvcResult response = mockMVC.perform(post(POST_CREATE_ENDERECO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoEndereco))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	void naoDeveCriarEnderecoPoisOCampoNumeroFoiFornecidoComValorAbaixoDoMinimo() throws Exception {

		NovoEnderecoDTO novoEndereco = new NovoEnderecoDTOBuilder().cep(65888254l).logradouro("Rua teste").numero(0l)
				.bairro("Bairro teste").cidade("Cidade teste").estado("Teste do norte").pais("Teste").build();
		MvcResult response = mockMVC.perform(post(POST_CREATE_ENDERECO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoEndereco))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	void naoDeveCriarEnderecoPoisOCampoNumeroFoiFornecidoComValorCimaDoMaximo() throws Exception {

		NovoEnderecoDTO novoEndereco = new NovoEnderecoDTOBuilder().cep(65888254l).logradouro("Rua teste")
				.numero(9999999999999999l).bairro("Bairro teste").cidade("Cidade teste").estado("Teste do norte")
				.pais("Teste").build();
		MvcResult response = mockMVC.perform(post(POST_CREATE_ENDERECO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoEndereco))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	void naoDeveCriarEnderecoPoisOCampoBairroNaoFoiFornecido() throws Exception {

		NovoEnderecoDTO novoEndereco = new NovoEnderecoDTOBuilder().cep(65888254l).logradouro("Rua teste").numero(666l)
				.cidade("Cidade teste").estado("Teste do norte").pais("Teste").build();
		MvcResult response = mockMVC.perform(post(POST_CREATE_ENDERECO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoEndereco))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	void naoDeveCriarEnderecoPoisOCampoCidadeNaoFoiFornecido() throws Exception {

		NovoEnderecoDTO novoEndereco = new NovoEnderecoDTOBuilder().cep(65888254l).logradouro("Rua teste").numero(666l)
				.bairro("Bairro teste").estado("Teste do norte").pais("Teste").build();
		MvcResult response = mockMVC.perform(post(POST_CREATE_ENDERECO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoEndereco))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	void naoDeveCriarEnderecoPoisOCampoEstadoNaoFoiFornecido() throws Exception {

		NovoEnderecoDTO novoEndereco = new NovoEnderecoDTOBuilder().cep(65888254l).logradouro("Rua teste").numero(666l)
				.bairro("Bairro teste").cidade("Cidade teste").pais("Teste").build();
		MvcResult response = mockMVC.perform(post(POST_CREATE_ENDERECO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoEndereco))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	void naoDeveCriarEnderecoPoisOCampoPaisNaoFoiFornecido() throws Exception {

		NovoEnderecoDTO novoEndereco = new NovoEnderecoDTOBuilder().cep(65888254l).logradouro("Rua teste").numero(666l)
				.bairro("Bairro teste").cidade("Cidade teste").estado("Teste do norte").build();
		MvcResult response = mockMVC.perform(post(POST_CREATE_ENDERECO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoEndereco))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	void naoDeveCriarEnderecoPoisJaExiste() throws JsonProcessingException, Exception {

		doThrow(EnderecoDuplicadoException.class).when(enderecoService).criarEndereco(any(NovoEnderecoDTO.class));

		NovoEnderecoDTO novoEndereco = new NovoEnderecoDTOBuilder().cep(25666357l).logradouro("Rua teste").numero(666l)
				.bairro("Bairro teste").cidade("Cidade teste").estado("Teste do norte").pais("Teste").build();
		MvcResult response = mockMVC.perform(post(POST_CREATE_ENDERECO).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(novoEndereco))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.CONFLICT);

	}

	@Test
	void deveListarTodosEnderecos() throws Exception {

		when(enderecoService.listarEnderecos()).thenReturn(new EnderecoDTOBuilder().quantidadeItens(10).buildList());

		MvcResult response = mockMVC.perform(get(GET_LIST_ALL_ENDERECO)).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.OK);
		assertThat(mapper.readValue(response.getResponse().getContentAsString(), EnderecosResponse.class).getEnderecos())
				.hasSize(10);

	}

	@Test
	void deveEditarEndereco() throws Exception {

		EditarEnderecoDTO editarEnderecoDTO = new EditarEnderecoDTOBuilder().bairro("Bairro teste")
				.cidade("Cidade teste").build();

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("cep", 12365478);
		URI editEndereco = UriComponentsBuilder.fromPath(PUT_EDIT_ENDERECO).buildAndExpand(variable).toUri();

		MvcResult response = mockMVC.perform(put(editEndereco).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(editarEnderecoDTO))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.NO_CONTENT);
		verify(enderecoService, times(1)).editarEndereco(anyLong(), any(EditarEnderecoDTO.class));

	}

	@Test
	void naoDeveEditarEnderecoPoisNaoFoiEncontrado() throws JsonProcessingException, Exception {

		doThrow(EnderecoNotFoundException.class).when(enderecoService).editarEndereco(anyLong(),
				any(EditarEnderecoDTO.class));

		EditarEnderecoDTO editarEnderecoDTO = new EditarEnderecoDTOBuilder().bairro("Bairro teste")
				.cidade("Cidade teste").build();

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("cep", 12365478);
		URI getEndereco = UriComponentsBuilder.fromPath(PUT_EDIT_ENDERECO).buildAndExpand(variable).toUri();

		MvcResult response = mockMVC.perform(put(getEndereco).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(editarEnderecoDTO))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.NOT_FOUND);

	}

	@Test
	void naoDeveEditarEnderecoPoisAsInformacoesNaoForamPreenchidos() throws JsonProcessingException, Exception {

		EditarEnderecoDTO editarEnderecoDTO = new EditarEnderecoDTOBuilder().build();

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("cep", 12365478);
		URI getEndereco = UriComponentsBuilder.fromPath(PUT_EDIT_ENDERECO).buildAndExpand(variable).toUri();

		MvcResult response = mockMVC.perform(put(getEndereco).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(editarEnderecoDTO))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	void naoDeveEditarPoisOCampoNumeroFoiFornecidoComValorAcimaDoMaximo() throws JsonProcessingException, Exception {

		EditarEnderecoDTO editarEnderecoDTO = new EditarEnderecoDTOBuilder().numero(999999999999999999l).build();

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("cep", 25666365);
		URI editEndereco = UriComponentsBuilder.fromPath(PUT_EDIT_ENDERECO).buildAndExpand(variable).toUri();

		MvcResult response = mockMVC.perform(put(editEndereco).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(editarEnderecoDTO))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.BAD_REQUEST);
		verify(enderecoService, never()).editarEndereco(anyLong(), any(EditarEnderecoDTO.class));

	}

	@Test
	void naoDeveEditarPoisOCampoNumeroFoiFornecidoComValorAbaixoDoMinimo() throws JsonProcessingException, Exception {

		EditarEnderecoDTO editarEnderecoDTO = new EditarEnderecoDTOBuilder().numero(0l).build();

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("cep", 25666365);
		URI editEndereco = UriComponentsBuilder.fromPath(PUT_EDIT_ENDERECO).buildAndExpand(variable).toUri();

		MvcResult response = mockMVC.perform(put(editEndereco).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(editarEnderecoDTO))).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.BAD_REQUEST);
		verify(enderecoService, never()).editarEndereco(anyLong(), any(EditarEnderecoDTO.class));

	}

	@Test
	void deveBuscarPorCEP() throws Exception {

		EnderecoCEPDetalheDTO enderecoCEPDetalheBuilt = new EnderecoCEPDetalheDTOBuilder().cep(25666357l)
				.logradouro("Rua teste").numero(666l).bairro("Bairro teste").cidade("Cidade teste")
				.estado("Teste do norte").pais("Teste").build();
		when(enderecoService.buscarPorCEP(anyLong())).thenReturn(enderecoCEPDetalheBuilt);

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("cep", 12365478);
		URI getEnderecoByCep = UriComponentsBuilder.fromPath(GET_ENDERECO_BY_CEP).buildAndExpand(variable).toUri();

		MvcResult response = mockMVC.perform(get(getEnderecoByCep)).andDo(print()).andReturn();
		EnderecoCEPDetalheDTO enderecoCEPDetalheDTO = mapper
				.readValue(response.getResponse().getContentAsString(), EnderecoCEPDetalheResponse.class)
				.getEnderecoCEPDetalheDTO();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.OK);
		assertThat(enderecoCEPDetalheDTO.getCep()).isEqualTo(enderecoCEPDetalheBuilt.getCep());
		assertEquals(enderecoCEPDetalheDTO.getLogradouro(), enderecoCEPDetalheBuilt.getLogradouro());
		assertEquals(enderecoCEPDetalheDTO.getNumero(), enderecoCEPDetalheBuilt.getNumero());
		assertEquals(enderecoCEPDetalheDTO.getBairro(), enderecoCEPDetalheBuilt.getBairro());
		assertEquals(enderecoCEPDetalheDTO.getCidade(), enderecoCEPDetalheBuilt.getCidade());
		assertEquals(enderecoCEPDetalheDTO.getEstado(), enderecoCEPDetalheBuilt.getEstado());
		assertEquals(enderecoCEPDetalheDTO.getPais(), enderecoCEPDetalheBuilt.getPais());

	}

	@Test
	public void naoDeveBuscarPorCEPPoisEnderecoNaoFoiEncontrado() throws Exception {

		doThrow(EnderecoNotFoundException.class).when(enderecoService).buscarPorCEP(anyLong());

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("cep", 12365478);
		URI getEnderecoByCep = UriComponentsBuilder.fromPath(GET_ENDERECO_BY_CEP).buildAndExpand(variable).toUri();

		MvcResult response = mockMVC.perform(get(getEnderecoByCep)).andDo(print()).andReturn();

		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.NOT_FOUND);

	}

	@Test
	void deveDeletarEndereco() throws Exception {

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("cep", 12365478);
		URI deleteByCep = UriComponentsBuilder.fromPath(DELETE_ENDERECO).buildAndExpand(variable).toUri();

		MvcResult response = mockMVC.perform(delete(deleteByCep)).andDo(print()).andReturn();

		verify(enderecoService, times(1)).deletarEndereco(anyLong());
		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.NO_CONTENT);

	}

	@Test
	void naoDeveDeletarEnderecoPoisEnderecoNaoFoiEncontrado() throws Exception {

		doThrow(EnderecoNotFoundException.class).when(enderecoService).deletarEndereco(anyLong());

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("cep", 12365478);
		URI deleteByCep = UriComponentsBuilder.fromPath(DELETE_ENDERECO).buildAndExpand(variable).toUri();

		MvcResult response = mockMVC.perform(delete(deleteByCep)).andDo(print()).andReturn();
		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.NOT_FOUND);

	}

	@Test
	void deveRetornarInternalServerError() throws Exception {

		doThrow(RuntimeException.class).when(enderecoService).deletarEndereco(anyLong());

		Map<String, Object> variable = new HashMap<String, Object>();
		variable.put("cep", 12365478);
		URI deleteByCep = UriComponentsBuilder.fromPath(DELETE_ENDERECO).buildAndExpand(variable).toUri();

		MvcResult response = mockMVC.perform(delete(deleteByCep)).andDo(print()).andReturn();
		assertThat(HttpStatus.valueOf(response.getResponse().getStatus())).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
