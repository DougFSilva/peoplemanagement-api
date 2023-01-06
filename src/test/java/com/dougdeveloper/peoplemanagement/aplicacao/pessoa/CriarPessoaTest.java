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

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosCriarPessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

class CriarPessoaTest {

	@Mock
	private PessoaRepository repository;

	private CriarPessoa criarPessoa;

	@Captor
	private ArgumentCaptor<Pessoa> pessoaCaptor;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.criarPessoa = new CriarPessoa(repository);
	}

	@Test
	void deveriaCriarUmaPessoa() {
		DadosCriarPessoa dadosCriarPessoa = new DadosCriarPessoa("Fulano da Silva", LocalDate.parse("1991-04-10"));
		Pessoa pessoa = new Pessoa(1l, dadosCriarPessoa.nome(), dadosCriarPessoa.dataNascimento(), new ArrayList<>());
		Mockito.when(repository.criar(Mockito.any())).thenReturn(pessoa);
		criarPessoa.executar(dadosCriarPessoa);
		Mockito.verify(repository).criar(pessoaCaptor.capture());
		Pessoa pessoaACriar = pessoaCaptor.getValue();
		assertEquals(dadosCriarPessoa.nome(), pessoaACriar.getNome());
		assertEquals(dadosCriarPessoa.dataNascimento(), pessoaACriar.getDataNascimento());
	}

}
