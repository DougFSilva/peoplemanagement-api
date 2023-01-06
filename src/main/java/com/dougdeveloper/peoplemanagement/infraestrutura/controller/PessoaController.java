package com.dougdeveloper.peoplemanagement.infraestrutura.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarDadosDePessoaPorId;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarDadosDePessoasPorCep;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarDadosDePessoasPorCidade;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarDadosDePessoasPorNome;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarDadosDeTodasPessoas;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.CriarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.DeletarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.EditarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosCriarEndereco;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosCriarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosEditarEndereco;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosEditarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco.AdicionarEnderecoAPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco.EditarEnderecoDaPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco.RemoverEnderecoDaPessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/pessoa")
@EnableCaching
public class PessoaController {

	@Autowired
	private AdicionarEnderecoAPessoa adicionarEnderecoAPessoa;

	@Autowired
	private BuscarDadosDePessoaPorId buscarDadosDePessoaPorId;

	@Autowired
	private BuscarDadosDePessoasPorCep buscarPessoasPorCep;

	@Autowired
	private BuscarDadosDePessoasPorCidade buscarPessoasPorCidade;

	@Autowired
	private BuscarDadosDePessoasPorNome buscarPessoasPorNome;

	@Autowired
	private BuscarDadosDeTodasPessoas buscarTodasPessoas;

	@Autowired
	private CriarPessoa criarPessoa;

	@Autowired
	private DeletarPessoa deletarPessoa;

	@Autowired
	private EditarEnderecoDaPessoa editarEnderecoDaPessoa;

	@Autowired
	private EditarPessoa editarPessoa;

	@Autowired
	private RemoverEnderecoDaPessoa removerEnderecoDaPessoa;

	@PostMapping
	@CacheEvict(value = { "buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas" }, allEntries = true)
	@Operation(summary = "Criar Pessoa", description = "Endpoint para criar uma pessoa no sistema")
	public ResponseEntity<Pessoa> criarPessoa(@RequestBody @Valid DadosCriarPessoa dados) {
		DadosDePessoa dadosDePessoa = criarPessoa.executar(dados);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dadosDePessoa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	@CacheEvict(value = { "buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas" }, allEntries = true)
	@Operation(summary = "Deletar Pessoa", description = "Endpoint para deletar uma pessoa cadastrada no sistema")
	public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
		deletarPessoa.executar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	@CacheEvict(value = { "buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas" }, allEntries = true)
	@Operation(summary = "Editar Pessoa", description = "Endpoint para editar uma pessoa cadastrada no sistema")
	public ResponseEntity<DadosDePessoa> editarPessoa(@PathVariable Long id, @RequestBody @Valid DadosEditarPessoa dados) {
		DadosDePessoa dadosDePessoa = editarPessoa.executar(id, dados);
		return ResponseEntity.ok().body(dadosDePessoa);
	}

	@PostMapping(value = "/{id}/adicionar-endereco")
	@CacheEvict(value = { "buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas" }, allEntries = true)
	@Operation(summary = "Adicionar Endereço à Pessoa", description = "Endpoint para adicionar um endereço à uma pessoa cadastrada no sistema")
	public ResponseEntity<DadosDePessoa> adicionarEndereco(@PathVariable Long id,
			@RequestBody @Valid DadosCriarEndereco dados) {
		DadosDePessoa dadosDePessoa = adicionarEnderecoAPessoa.executar(id, dados);
		return ResponseEntity.ok().body(dadosDePessoa);
	}

	@DeleteMapping(value = "/{id}/remover-endereco/{enderecoId}")
	@CacheEvict(value = { "buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas" }, allEntries = true)
	@Operation(summary = "Remover Endereço da Pessoa", description = "Endpoint para remover um endereço de uma pessoa cadastrada no sistema")
	public ResponseEntity<DadosDePessoa> removerEndereco(@PathVariable Long id, @PathVariable Long enderecoId) {
		DadosDePessoa dadosDePessoa = removerEnderecoDaPessoa.executar(id, enderecoId);
		return ResponseEntity.ok().body(dadosDePessoa);
	}

	@PutMapping(value = "/{id}/editar-endereco")
	@CacheEvict(value = { "buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas" }, allEntries = true)
	@Operation(summary = "Editar Endereço da Pessoa", description = "Endpoint para editar um endereço de uma pessoa cadastrada no sistema")
	public ResponseEntity<DadosDePessoa> editarEndereco(@PathVariable Long id,
			@RequestBody @Valid DadosEditarEndereco dados) {
		DadosDePessoa dadosDePessoa = editarEnderecoDaPessoa.executar(id, dados);
		return ResponseEntity.ok().body(dadosDePessoa);
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "Buscar Dados de Pessoa pelo Id", description = "Endpoint para buscar os dados de uma pessoa cadastrada no sistema")
	public ResponseEntity<DadosDePessoa> buscarPorId(@PathVariable Long id) {
		DadosDePessoa dadosDePessoa = buscarDadosDePessoaPorId.executar(id);
		return ResponseEntity.ok().body(dadosDePessoa);
	}

	@GetMapping(value = "/cep/{cep}")
	@Cacheable(value = "buscarPorCep")
	@Operation(summary = "Buscar Dados de Pessoas pelo Cep", description = "Endpoint para buscar os dados de pessoas cadastradas no sistema "
			+ "pelo Cep")
	public ResponseEntity<Page<DadosDePessoa>> buscarPorCep(@PathVariable String cep, Pageable paginacao) {
		Page<DadosDePessoa> dadosDePessoas = buscarPessoasPorCep.executar(cep, paginacao);
		return ResponseEntity.ok().body(dadosDePessoas);
	}

	@GetMapping(value = "/cidade/{cidade}")
	@Cacheable(value = "buscarPorCidade")
	@Operation(summary = "Buscar Dados de Pessoas pela Cidade", description = "Endpoint para buscar os dados de todas as pessoas cadastradas "
			+ "no sistema pela cidade")
	public ResponseEntity<Page<DadosDePessoa>> buscarPorCidade(@PathVariable String cidade, Pageable paginacao) {
		Page<DadosDePessoa> dadosDePessoas = buscarPessoasPorCidade.executar(cidade, paginacao);
		return ResponseEntity.ok().body(dadosDePessoas);
	}

	@GetMapping(value = "/nome/{nome}")
	@Cacheable(value = "buscarPorNome")
	@Operation(summary = "Buscar Dados de Pessoas pela nome", description = "Endpoint para buscar os dados de todas as pessoas cadastradas "
			+ "no sistema pelo nome. A busca é realizada buscando todos as pessoas que contenham o nome desejado, ou parte dele")
	public ResponseEntity<List<DadosDePessoa>> buscarPorNome(@PathVariable String nome) {
		List<DadosDePessoa> dadosDePessoas = buscarPessoasPorNome.executar(nome);
		return ResponseEntity.ok().body(dadosDePessoas);
	}

	@GetMapping
	@Cacheable(value = "buscarTodas")
	@Operation(summary = "Buscar Dados de Pessoas", description = "Endpoint para buscar os dados de todas as pessoas cadastradas no sistema")
	public ResponseEntity<Page<DadosDePessoa>> buscarTodas(Pageable paginacao) {
		Page<DadosDePessoa> dadosDePessoas = buscarTodasPessoas.executar(paginacao);
		return ResponseEntity.ok().body(dadosDePessoas);
	}

}
