package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosCriarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

class CriarPessoaTest {

	@Mock
	private PessoaRepository repository;

	private CriarPessoa criarPessoa;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.criarPessoa = new CriarPessoa(repository);
	}

	@Test
	void deveriaCriarUmaPessoa() {
		Pessoa pessoa = new Pessoa(1l, "Fulano da Silva", LocalDate.parse("1991-04-10"), new ArrayList<>());
		DadosCriarPessoa dadosCriarPessoa = new DadosCriarPessoa("Fulano da Silva", LocalDate.parse("1991-04-10"));
		Mockito.when(repository.criar(Mockito.any())).thenReturn(pessoa);
		DadosDePessoa dadosDePessoa = criarPessoa.executar(dadosCriarPessoa);
		assertEquals(dadosDePessoa.getId(), pessoa.getId());
		assertEquals(dadosDePessoa.getNome(), pessoa.getNome());
		assertEquals(dadosDePessoa.getDataNascimento(),
				pessoa.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		assertEquals(dadosDePessoa.getEnderecos().size(), 0);

	}

}
