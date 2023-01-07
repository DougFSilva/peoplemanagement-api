package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosEditarEndereco;
import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;
import com.dougdeveloper.peoplemanagement.dominio.logger.AppLogger;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Cep;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

class EditaEnderecoDaPessoaTest {

	@Mock
	private PessoaRepository repository;

	@Mock
	private AppLogger logger;

	private EditaEnderecoDaPessoa editaEnderecoDaPessoa;

	@Captor
	private ArgumentCaptor<Pessoa> pessoaCaptor;

	@Captor
	private ArgumentCaptor<Class<?>> loggerClassCaptor;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.editaEnderecoDaPessoa = new EditaEnderecoDaPessoa(repository, logger);
	}

	@Test
	void deveriaEditarUmEnderecoDeUmaPessoa() {
		Pessoa pessoa = pessoa();
		DadosEditarEndereco dadosEditarEndereco = new DadosEditarEndereco(2l, "Rua Benjamin Franklin", "18125-333",
				"400", "Campinas", false);
		Mockito.when(repository.editar(Mockito.any())).thenReturn(pessoa);
		Mockito.when(repository.buscarPorId(Mockito.anyLong())).thenReturn(Optional.of(pessoa));
		editaEnderecoDaPessoa.editar(pessoa.getId(), dadosEditarEndereco);
		Mockito.verify(repository).editar(pessoaCaptor.capture());
		Mockito.verify(logger).info(Mockito.anyString(), loggerClassCaptor.capture());
		Endereco enderecoEditado = pessoaCaptor.getValue().getEnderecos().get(1);
		assertEquals(EditaEnderecoDaPessoa.class, loggerClassCaptor.getValue());
		assertEquals(dadosEditarEndereco.logradouro(), enderecoEditado.getLogradouro());
		assertEquals(dadosEditarEndereco.cep(), enderecoEditado.getCep().getDigitos());
		assertEquals(dadosEditarEndereco.numero(), enderecoEditado.getNumero());
		assertEquals(dadosEditarEndereco.cidade(), enderecoEditado.getCidade());
		assertEquals(dadosEditarEndereco.principal(), enderecoEditado.isPrincipal());
	}

	@Test
	void aoEditarOEnderecoDeUmaPessoaComoPrincipalDeveriaSetarTodosOsOutroEnderecosComoNaoPrincipal() {
		Pessoa pessoa = pessoa();
		DadosEditarEndereco dadosEditarEndereco = new DadosEditarEndereco(2l, "Rua Benjamin Franklin", "18125-333",
				"400", "Campinas", true);
		Mockito.when(repository.editar(Mockito.any())).thenReturn(pessoa);
		Mockito.when(repository.buscarPorId(Mockito.anyLong())).thenReturn(Optional.of(pessoa));
		editaEnderecoDaPessoa.editar(pessoa.getId(), dadosEditarEndereco);
		Mockito.verify(repository).editar(pessoaCaptor.capture());
		Endereco endereco = pessoaCaptor.getValue().getEnderecos().get(0);
		assertTrue(!endereco.isPrincipal());
	}

	@Test
	void naoDeveriaTentarEditarUmEnderecoQueNaoExiste() {
		Mockito.when(repository.buscarPorId(1l)).thenReturn(Optional.empty());
		DadosEditarEndereco dadosEditarEndereco = new DadosEditarEndereco(2l, "Rua Benjamin Franklin", "18125-333",
				"400", "Campinas", true);
		assertThrows(ObjetoNaoEncontradoException.class, () -> editaEnderecoDaPessoa.editar(1l, dadosEditarEndereco));
		Mockito.verify(repository, never()).editar(Mockito.any());
		Mockito.verifyNoInteractions(logger);
	}

	private Pessoa pessoa() {
		Pessoa pessoa = new Pessoa(1l, "Fulano da Silva", LocalDate.parse("1991-04-10"), new ArrayList<>());
		Endereco endereco = new Endereco(1l, "Rua Albert Einstein", new Cep("18125-000"), "100", "Sorocaba", true);
		Endereco outroEndereco = new Endereco(2l, "Rua Nicola Tesla", new Cep("18125-111"), "300", "São Roque", false);
		pessoa.adicionarEndereco(endereco);
		pessoa.adicionarEndereco(outroEndereco);
		return pessoa;
	}

}
