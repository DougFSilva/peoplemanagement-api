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
import com.dougdeveloper.peoplemanagement.dominio.logger.AppLogger;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

class DeletaPessoaTest {

	@Mock
	private PessoaRepository repository;
	
	@Mock
	private AppLogger logger;
	
	private DeletaPessoa deletaPessoa;
	
	@Captor
	private ArgumentCaptor<Pessoa> pessoaCaptor;
	
	@Captor
	private ArgumentCaptor<Class<?>> loggerClassCaptor;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.deletaPessoa = new DeletaPessoa(repository, logger);
	}

	@Test
	void deveriaDeletarUmaPessoa() {
		Long id = 1l;
		Pessoa pessoa = new Pessoa(id, "Fulano da Silva", LocalDate.parse("1991-04-10"), new ArrayList<>());
		Mockito.when(repository.buscarPorId(id)).thenReturn(Optional.of(pessoa));
		deletaPessoa.deletar(id);
		Mockito.verify(repository).deletar(pessoaCaptor.capture());
		Mockito.verify(logger).info(Mockito.anyString(), loggerClassCaptor.capture());
		assertEquals(DeletaPessoa.class, loggerClassCaptor.getValue());
		assertEquals(id, pessoaCaptor.getValue().getId());
	}
	
	@Test
	void naoDeveriaTentarDeletarUmaPessoaQueNaoExiste() {
		Long id = 1l;
		Mockito.when(repository.buscarPorId(id)).thenReturn(Optional.empty());
		assertThrows(ObjetoNaoEncontradoException.class, () -> deletaPessoa.deletar(id));
		Mockito.verify(repository, never()).deletar(Mockito.any());
		Mockito.verifyNoInteractions(logger);
	}

}
