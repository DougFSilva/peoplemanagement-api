package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosCriarOuEditarPessoa;
import com.dougdeveloper.peoplemanagement.dominio.logger.AppLogger;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

class CriaPessoaTest {

	@Mock
	private PessoaRepository repository;
	
	@Mock
	private AppLogger logger;

	private CriaPessoa criaPessoa;

	@Captor
	private ArgumentCaptor<Pessoa> pessoaCaptor;
	
	@Captor
	private ArgumentCaptor<Class<?>> loggerClassCaptor;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.criaPessoa = new CriaPessoa(repository, logger);
	}

	@Test
	void deveriaCriarUmaPessoa() {
		DadosCriarOuEditarPessoa dadosCriarPessoa = new DadosCriarOuEditarPessoa("Fulano da Silva", LocalDate.parse("1991-04-10"));
		Pessoa pessoa = new Pessoa(1l, dadosCriarPessoa.nome(), dadosCriarPessoa.dataNascimento(), new ArrayList<>());
		Mockito.when(repository.criar(Mockito.any())).thenReturn(pessoa);
		criaPessoa.criar(dadosCriarPessoa);
		Mockito.verify(repository).criar(pessoaCaptor.capture());
		Mockito.verify(logger).info(Mockito.anyString(), loggerClassCaptor.capture());
		Pessoa pessoaACriar = pessoaCaptor.getValue();
		assertEquals(CriaPessoa.class, loggerClassCaptor.getValue());
		assertEquals(dadosCriarPessoa.nome(), pessoaACriar.getNome());
		assertEquals(dadosCriarPessoa.dataNascimento(), pessoaACriar.getDataNascimento());
	}

}
