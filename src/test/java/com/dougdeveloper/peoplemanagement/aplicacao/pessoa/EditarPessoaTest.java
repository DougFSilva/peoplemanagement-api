package com.dougdeveloper.peoplemanagement.aplicacao.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosEditarPessoa;
import com.dougdeveloper.peoplemanagement.dominio.exception.ObjetoNaoEncontradoException;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

class EditarPessoaTest {

	@Mock
	private PessoaRepository repository;

	private EditarPessoa editarPessoa;

	@Captor
	private ArgumentCaptor<Pessoa> pessoaCaptor;

	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.openMocks(this);
		this.editarPessoa = new EditarPessoa(repository);
	}

	@Test
	void deveriaEditarUmaPessoa() {
		Long id = 1l;
		Pessoa pessoa = new Pessoa(id, "Fulano da Silva", LocalDate.parse("1991-04-10"), new ArrayList<>());
		DadosEditarPessoa dadosEditarPessoa = new DadosEditarPessoa("Sicrano da Silva", LocalDate.parse("1992-05-20"));
		Mockito.when(repository.buscarPorId(id)).thenReturn(Optional.of(pessoa));
		Mockito.when(repository.editar(Mockito.any())).thenReturn(new Pessoa(pessoa.getId(), dadosEditarPessoa.nome(),
				dadosEditarPessoa.dataNascimento(), pessoa.getEnderecos()));
		DadosDePessoa dadosDePessoa = editarPessoa.executar(id, dadosEditarPessoa);
		Mockito.verify(repository).editar(pessoaCaptor.capture());
		assertEquals(pessoaCaptor.getValue().getNome(), dadosEditarPessoa.nome());
		assertEquals(pessoaCaptor.getValue().getDataNascimento(), dadosEditarPessoa.dataNascimento());
		assertEquals(dadosDePessoa.getNome(), dadosEditarPessoa.nome());
		assertEquals(dadosDePessoa.getDataNascimento(), dadosEditarPessoa.dataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}
	
	@Test
	void naoDeveriaTentarEditarUmaPessoaQueNaoExiste() {
		Long id = 1l;
		Mockito.when(repository.buscarPorId(id)).thenReturn(Optional.empty());
		assertThrows(ObjetoNaoEncontradoException.class, () -> editarPessoa.executar(id, Mockito.any()));
		Mockito.verify(repository, never()).editar(Mockito.any());
	}

}
