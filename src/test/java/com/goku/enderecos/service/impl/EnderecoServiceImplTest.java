package com.goku.enderecos.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.goku.enderecos.builder.EditarEnderecoDTOBuilder;
import com.goku.enderecos.builder.EnderecoBuilder;
import com.goku.enderecos.builder.NovoEnderecoDTOBuilder;
import com.goku.enderecos.dto.EditarEnderecoDTO;
import com.goku.enderecos.dto.EnderecoCEPDetalheDTO;
import com.goku.enderecos.dto.EnderecoDTO;
import com.goku.enderecos.dto.NovoEnderecoDTO;
import com.goku.enderecos.exception.EnderecoDuplicadoException;
import com.goku.enderecos.exception.EnderecoNotFoundException;
import com.goku.enderecos.model.Endereco;
import com.goku.enderecos.repository.EnderecoRepository;

@SpringBootTest
public class EnderecoServiceImplTest {

	@Mock
	private EnderecoRepository enderecoRepository;

	@InjectMocks
	private EnderecoServiceImpl enderecoService;

	@Test
	public void shouldCriarEndereco() {

		Endereco enderecoBuild = null;
		when(enderecoRepository.findByCep(anyLong())).thenReturn(Optional.ofNullable(enderecoBuild));

		NovoEnderecoDTO novoEndereco = new NovoEnderecoDTOBuilder().cep(25666357l).logradouro("Rua teste").numero(666l)
				.bairro("Bairro teste").cidade("Cidade teste").estado("Teste do norte").pais("Teste").build();

		enderecoService.criarEndereco(novoEndereco);

		ArgumentCaptor<Endereco> argCaptor = ArgumentCaptor.forClass(Endereco.class);
		verify(enderecoRepository, times(1)).save(argCaptor.capture());

		assertEquals(argCaptor.getValue().getCep(), novoEndereco.getCep());
		assertEquals(argCaptor.getValue().getLogradouro(), novoEndereco.getLogradouro());
		assertEquals(argCaptor.getValue().getNumero(), novoEndereco.getNumero());
		assertEquals(argCaptor.getValue().getBairro(), novoEndereco.getBairro());
		assertEquals(argCaptor.getValue().getCidade(), novoEndereco.getCidade());
		assertEquals(argCaptor.getValue().getEstado(), novoEndereco.getEstado());
		assertEquals(argCaptor.getValue().getPais(), novoEndereco.getPais());

	}

	@Test
	public void naoDeveCriarEnderecoPoisJaExiste() {

		Endereco enderecoBuild = new EnderecoBuilder().cep(25666357l).logradouro("Rua teste").numero(666l)
				.bairro("Bairro teste").cidade("Cidade teste").estado("Teste do norte").pais("Teste").build();
		when(enderecoRepository.findByCep(anyLong())).thenReturn(Optional.ofNullable(enderecoBuild));

		assertThrows(EnderecoDuplicadoException.class, () -> {
			enderecoService.criarEndereco(new NovoEnderecoDTOBuilder().cep(254212l).build());
		});

		verify(enderecoRepository, never()).save(any(Endereco.class));

	}

	@Test
	public void shouldListarEnderecos() {

		when(enderecoRepository.findAll()).thenReturn(new EnderecoBuilder().quantidadeItens(50).buildList());

		List<EnderecoDTO> enderecos = enderecoService.listarEnderecos();

		assertThat(enderecos).hasSize(50);

	}

	@Test
	public void shouldEditarEndereco() {

		Endereco enderecoBuild = new EnderecoBuilder().cep(25666357l).logradouro("Rua teste").numero(666l)
				.bairro("Bairro teste").cidade("Cidade teste").estado("Teste do norte").pais("Teste").build();
		when(enderecoRepository.findByCep(anyLong())).thenReturn(Optional.ofNullable(enderecoBuild));

		EditarEnderecoDTO editarEnderecoBuild = new EditarEnderecoDTOBuilder().bairro("bairro alterado")
				.cidade("cidade alterada").build();
		enderecoService.editarEndereco(25666352l, editarEnderecoBuild);

		ArgumentCaptor<Endereco> argCaptor = ArgumentCaptor.forClass(Endereco.class);
		verify(enderecoRepository, times(1)).save(argCaptor.capture());

		assertThat(argCaptor.getValue().getBairro()).isEqualTo(editarEnderecoBuild.getBairro());
		assertThat(argCaptor.getValue().getCidade()).isEqualTo(editarEnderecoBuild.getCidade());

	}

	@Test
	public void shouldNotEditEnderecoBecauseEnderecoWasNotFound() {

		Endereco enderecoBuild = null;
		when(enderecoRepository.findByCep(anyLong())).thenReturn(Optional.ofNullable(enderecoBuild));

		EditarEnderecoDTO editarEnderecoBuild = new EditarEnderecoDTOBuilder().bairro("bairro alterado")
				.cidade("cidade alterada").build();

		assertThrows(EnderecoNotFoundException.class, () -> {
			enderecoService.editarEndereco(25666352l, editarEnderecoBuild);
		});

		verify(enderecoRepository, never()).save(any());
	}

	@Test
	public void shouldBuscarPorCEP() {

		Endereco enderecoBuild = new EnderecoBuilder().cep(25666357l).logradouro("Rua teste").numero(666l)
				.bairro("Bairro teste").cidade("Cidade teste").estado("Teste do norte").pais("Teste").build();
		when(enderecoRepository.findByCep(anyLong())).thenReturn(Optional.ofNullable(enderecoBuild));

		EnderecoCEPDetalheDTO enderecoCEPDetalheDTO = enderecoService.buscarPorCEP(25666951l);
		assertThat(enderecoCEPDetalheDTO.getCep()).isEqualTo(enderecoBuild.getCep());
		assertThat(enderecoCEPDetalheDTO.getLogradouro()).isEqualTo(enderecoBuild.getLogradouro());
		assertThat(enderecoCEPDetalheDTO.getNumero()).isEqualTo(enderecoBuild.getNumero());
		assertThat(enderecoCEPDetalheDTO.getBairro()).isEqualTo(enderecoBuild.getBairro());
		assertThat(enderecoCEPDetalheDTO.getCidade()).isEqualTo(enderecoBuild.getCidade());
		assertThat(enderecoCEPDetalheDTO.getEstado()).isEqualTo(enderecoBuild.getEstado());
		assertThat(enderecoCEPDetalheDTO.getPais()).isEqualTo(enderecoBuild.getPais());

	}

	@Test
	public void shouldNotFindByCepBecauseEnderecoWasNotFound() {

		Endereco enderecoBuild = null;
		when(enderecoRepository.findByCep(anyLong())).thenReturn(Optional.ofNullable(enderecoBuild));

		assertThrows(EnderecoNotFoundException.class, () -> {
			enderecoService.buscarPorCEP(25666951l);
		});

	}

	@Test
	public void shouldDeleteEndereco() {

		Endereco enderecoBuild = new EnderecoBuilder().id(10l).cep(25666357l).logradouro("Rua teste").numero(666l)
				.bairro("Bairro teste").cidade("Cidade teste").estado("Teste do norte").pais("Teste").build();
		when(enderecoRepository.findByCep(anyLong())).thenReturn(Optional.ofNullable(enderecoBuild));

		enderecoService.deletarEndereco(69555258l);

		ArgumentCaptor<Long> argCaptor = ArgumentCaptor.forClass(Long.class);
		verify(enderecoRepository, times(1)).deleteById(argCaptor.capture());

		assertEquals(argCaptor.getValue(), 10l);

	}

	@Test
	public void sholdNotDeleteEnderecoBecauseEnderecoWasNotFound() {

		Endereco enderecoBuild = null;
		when(enderecoRepository.findByCep(anyLong())).thenReturn(Optional.ofNullable(enderecoBuild));

		assertThrows(EnderecoNotFoundException.class, () -> {
			enderecoService.deletarEndereco(69555258l);
		});
		verify(enderecoRepository, never()).deleteById(anyLong());

	}

}
