package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

class DeletarPessoaTest {

	@Mock
	private PessoaRepository repository;
	
	private DeletarPessoa deletarPessoa;
	
	@Captor
	private ArgumentCaptor<Pessoa> pessoaCaptor;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.deletarPessoa = new DeletarPessoa(repository);
	}

	@Test
	void deveriaDeletarUmaPessoa() {
		Long id = 1l;
		Pessoa pessoa = new Pessoa(id, "Fulano da Silva", LocalDate.parse("1991-04-10"), new ArrayList<>());
		Mockito.when(repository.buscarPorId(id)).thenReturn(Optional.of(pessoa));
		deletarPessoa.executar(id);
		Mockito.verify(repository).deletar(pessoaCaptor.capture());
		assertEquals(id, pessoaCaptor.getValue().getId());
	}
	
	@Test
	void naoDeveriaTentarDeletarUmaPessoaQueNaoExiste() {
		Long id = 1l;
		Mockito.when(repository.buscarPorId(id)).thenReturn(Optional.empty());
		assertThrows(ObjetoNaoEncontradoException.class, () -> deletarPessoa.executar(id));
		Mockito.verify(repository, never()).deletar(Mockito.any());
	}

}
