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

import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Cep;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

class RemoveEnderecoDaPessoaTest {

	@Mock
	private PessoaRepository repository;

	private RemoveEnderecoDaPessoa removeEnderecoDaPessoa;

	@Captor
	private ArgumentCaptor<Pessoa> pessoaCaptor;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.removeEnderecoDaPessoa = new RemoveEnderecoDaPessoa(repository);
	}

	@Test
	void deveriaRemoverUmEnderecoDeUmaPessoa() {
		Pessoa pessoa = pessoa();
		Mockito.when(repository.buscarPorId(1l)).thenReturn(Optional.of(pessoa));
		Mockito.when(repository.editar(Mockito.any())).thenReturn(pessoa);
		removeEnderecoDaPessoa.remover(1l, 1l);
		Mockito.verify(repository).editar(pessoaCaptor.capture());
		assertTrue(pessoaCaptor.getValue().getEnderecos().size() == 1);
		assertEquals(2l, pessoaCaptor.getValue().getEnderecos().get(0).getId());
	}

	@Test
	void naoDeveriaTentarRemoverUmEnderecoDeUmaPessoaQueNaoExiste() {
		Mockito.when(repository.buscarPorId(1l)).thenReturn(Optional.empty());
		assertThrows(ObjetoNaoEncontradoException.class, () -> removeEnderecoDaPessoa.remover(1l, Mockito.any()));
		Mockito.verify(repository, never()).editar(Mockito.any());
	}

	private Pessoa pessoa() {
		Pessoa pessoa = new Pessoa(1l, "Fulano da Silva", LocalDate.parse("1991-04-10"), new ArrayList<>());
		Endereco endereco = new Endereco(1l, "Rua Albert Einstein", new Cep("18125-000"), "100", "Sorocaba", true);
		Endereco outroEndereco = new Endereco(2l, "Rua Nicola Tesla", new Cep("18125-111"), "200", "São Roque", true);
		pessoa.adicionarEndereco(endereco);
		pessoa.adicionarEndereco(outroEndereco);
		return pessoa;
	}

}
