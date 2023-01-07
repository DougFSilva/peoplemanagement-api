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

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosCriarEndereco;
import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;
import com.dougdeveloper.peoplemanagement.dominio.logger.AppLogger;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Cep;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

class AdicionaEnderecoAPessoaTest {

	@Mock
	private PessoaRepository repository;
	
	@Mock
	private AppLogger logger;

	private AdicionaEnderecoAPessoa adicionaEnderecoAPessoa;

	@Captor
	private ArgumentCaptor<Pessoa> pessoaCaptor;
	
	@Captor
	private ArgumentCaptor<Class<?>> loggerClassCaptor;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.adicionaEnderecoAPessoa = new AdicionaEnderecoAPessoa(repository, logger);
	}

	@Test
	void deveriaAdicionarUmEnderecoAUmaPessoa() {
		Pessoa pessoa = pessoa();
		Mockito.when(repository.buscarPorId(1l)).thenReturn(Optional.of(pessoa));
		Mockito.when(repository.editar(Mockito.any())).thenReturn(pessoa);
		DadosCriarEndereco dadosCriarEndereco = new DadosCriarEndereco("Rua Nicola Tesla", "18125-111", "200",
				"São Roque", false);
		adicionaEnderecoAPessoa.adicionar(1l, dadosCriarEndereco);
		Mockito.verify(repository).editar(pessoaCaptor.capture());
		Mockito.verify(logger).info(Mockito.anyString(), loggerClassCaptor.capture());
		Endereco novoEndereco = pessoaCaptor.getValue().getEnderecos().get(1);
		assertEquals(AdicionaEnderecoAPessoa.class, loggerClassCaptor.getValue());
		assertTrue(pessoaCaptor.getValue().getEnderecos().size() == 2);
		assertEquals(dadosCriarEndereco.logradouro(), novoEndereco.getLogradouro());
		assertEquals(dadosCriarEndereco.cep(), novoEndereco.getCep().getDigitos());
		assertEquals(dadosCriarEndereco.numero(), novoEndereco.getNumero());
		assertEquals(dadosCriarEndereco.cidade(), novoEndereco.getCidade());
		assertEquals(dadosCriarEndereco.principal(), novoEndereco.isPrincipal());

	}

	@Test
	void aoAdicionarUmNovoEnderecoComoPrincipalDeveriaSetarTodosOsOutrosComoNaoPrincipal() {
		Pessoa pessoa = pessoa();
		Mockito.when(repository.buscarPorId(1l)).thenReturn(Optional.of(pessoa));
		Mockito.when(repository.editar(Mockito.any())).thenReturn(pessoa);
		DadosCriarEndereco dadosCriarEndereco = new DadosCriarEndereco("Rua Nicola Tesla", "18125-111", "200", "São Roque", true);
		adicionaEnderecoAPessoa.adicionar(1l, dadosCriarEndereco);
		Mockito.verify(repository).editar(pessoaCaptor.capture());
		assertTrue(!pessoaCaptor.getValue().getEnderecos().get(0).isPrincipal());
	}

	@Test
	void naoDeveriaTentarAdicionarUmEnderecoAUmaPessoaQueNaoExiste() {
		Mockito.when(repository.buscarPorId(1l)).thenReturn(Optional.empty());
		DadosCriarEndereco dadosCriarEndereco = new DadosCriarEndereco("Rua Nicola Tesla", "18125-111", "200",
				"São Roque", false);
		assertThrows(ObjetoNaoEncontradoException.class,
				() -> adicionaEnderecoAPessoa.adicionar(1l, dadosCriarEndereco));
		Mockito.verify(repository, never()).editar(Mockito.any());
		Mockito.verifyNoInteractions(logger);
	}

	private Pessoa pessoa() {
		Pessoa pessoa = new Pessoa(1l, "Fulano da Silva", LocalDate.parse("1991-04-10"), new ArrayList<>());
		Endereco endereco = new Endereco(1l, "Rua Albert Einstein", new Cep("18125-000"), "100", "Sorocaba", true);
		pessoa.adicionarEndereco(endereco);
		return pessoa;
	}

}
