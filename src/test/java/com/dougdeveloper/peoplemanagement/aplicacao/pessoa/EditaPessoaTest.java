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

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosCriarOuEditarPessoa;
import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;
import com.dougdeveloper.peoplemanagement.dominio.logger.AppLogger;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

class EditaPessoaTest {

	@Mock
	private PessoaRepository repository;

	@Mock
	private AppLogger logger;

	private EditaPessoa editaPessoa;

	@Captor
	private ArgumentCaptor<Pessoa> pessoaCaptor;

	@Captor
	private ArgumentCaptor<Class<?>> loggerClassCaptor;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.editaPessoa = new EditaPessoa(repository, logger);
	}

	@Test
	void deveriaEditarUmaPessoa() {
		Long id = 1l;
		Pessoa pessoa = new Pessoa(id, "Fulano da Silva", LocalDate.parse("1991-04-10"), new ArrayList<>());
		DadosCriarOuEditarPessoa dadosEditarPessoa = new DadosCriarOuEditarPessoa("Sicrano da Silva",
				LocalDate.parse("1992-05-20"));
		Mockito.when(repository.buscarPorId(id)).thenReturn(Optional.of(pessoa));
		Mockito.when(repository.editar(Mockito.any())).thenReturn(pessoa);
		editaPessoa.editar(id, dadosEditarPessoa);
		Mockito.verify(repository).editar(pessoaCaptor.capture());
		Mockito.verify(logger).info(Mockito.anyString(), loggerClassCaptor.capture());
		assertEquals(EditaPessoa.class, loggerClassCaptor.getValue());
		assertEquals(dadosEditarPessoa.nome(), pessoaCaptor.getValue().getNome());
		assertEquals(dadosEditarPessoa.dataNascimento(), pessoaCaptor.getValue().getDataNascimento());
	}

	@Test
	void naoDeveriaTentarEditarUmaPessoaQueNaoExiste() {
		Long id = 1l;
		Mockito.when(repository.buscarPorId(id)).thenReturn(Optional.empty());
		assertThrows(ObjetoNaoEncontradoException.class, () -> editaPessoa.editar(id, Mockito.any()));
		Mockito.verify(repository, never()).editar(Mockito.any());
		Mockito.verifyNoInteractions(logger);
	}

}
