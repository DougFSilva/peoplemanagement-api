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

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscaDadosDePessoas;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.CriaPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.DeletaPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.EditaPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosCriarEndereco;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosCriarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosEditarEndereco;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.dto.DadosEditarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco.AdicionaEnderecoAPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco.EditaEnderecoDaPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.endereco.RemoveEnderecoDaPessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
/**
 * A classe <b>PessoaController</b> é responsável por receber as requisições HTTP referentes a manipulação de objetos do tipo <b>Pessoa</b>
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/pessoa")
@EnableCaching
public class PessoaController {

	@Autowired
	private AdicionaEnderecoAPessoa adicionaEnderecoAPessoa;

	@Autowired
	private BuscaDadosDePessoas buscaDadosDePessoas;

	@Autowired
	private CriaPessoa criaPessoa;

	@Autowired
	private DeletaPessoa deletaPessoa;

	@Autowired
	private EditaEnderecoDaPessoa editaEnderecoDaPessoa;

	@Autowired
	private EditaPessoa editaPessoa;

	@Autowired
	private RemoveEnderecoDaPessoa removeEnderecoDaPessoa;

	@PostMapping
	@CacheEvict(value = { "buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas" }, allEntries = true)
	@Operation(summary = "Criar Pessoa", description = "Endpoint para criar uma pessoa no sistema")
	public ResponseEntity<Pessoa> criarPessoa(@RequestBody @Valid DadosCriarPessoa dados) {
		DadosDePessoa dadosDePessoa = criaPessoa.criar(dados);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dadosDePessoa.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	@CacheEvict(value = { "buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas" }, allEntries = true)
	@Operation(summary = "Deletar Pessoa", description = "Endpoint para deletar uma pessoa cadastrada no sistema")
	public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
		deletaPessoa.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	@CacheEvict(value = { "buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas" }, allEntries = true)
	@Operation(summary = "Editar Pessoa", description = "Endpoint para editar uma pessoa cadastrada no sistema")
	public ResponseEntity<DadosDePessoa> editarPessoa(@PathVariable Long id,
			@RequestBody @Valid DadosEditarPessoa dados) {
		DadosDePessoa dadosDePessoa = editaPessoa.editar(id, dados);
		return ResponseEntity.ok().body(dadosDePessoa);
	}

	@PostMapping(value = "/{id}/adicionar-endereco")
	@CacheEvict(value = { "buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas" }, allEntries = true)
	@Operation(summary = "Adicionar Endereço à Pessoa", description = "Endpoint para adicionar um endereço à uma pessoa cadastrada no sistema")
	public ResponseEntity<DadosDePessoa> adicionarEndereco(@PathVariable Long id,
			@RequestBody @Valid DadosCriarEndereco dados) {
		DadosDePessoa dadosDePessoa = adicionaEnderecoAPessoa.adicionar(id, dados);
		return ResponseEntity.ok().body(dadosDePessoa);
	}

	@DeleteMapping(value = "/{id}/remover-endereco/{enderecoId}")
	@CacheEvict(value = { "buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas" }, allEntries = true)
	@Operation(summary = "Remover Endereço da Pessoa", description = "Endpoint para remover um endereço de uma pessoa cadastrada no sistema")
	public ResponseEntity<DadosDePessoa> removerEndereco(@PathVariable Long id, @PathVariable Long enderecoId) {
		DadosDePessoa dadosDePessoa = removeEnderecoDaPessoa.remover(id, enderecoId);
		return ResponseEntity.ok().body(dadosDePessoa);
	}

	@PutMapping(value = "/{id}/editar-endereco")
	@CacheEvict(value = { "buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas" }, allEntries = true)
	@Operation(summary = "Editar Endereço da Pessoa", description = "Endpoint para editar um endereço de uma pessoa cadastrada no sistema")
	public ResponseEntity<DadosDePessoa> editarEndereco(@PathVariable Long id,
			@RequestBody @Valid DadosEditarEndereco dados) {
		DadosDePessoa dadosDePessoa = editaEnderecoDaPessoa.editar(id, dados);
		return ResponseEntity.ok().body(dadosDePessoa);
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "Buscar Dados de Pessoa pelo Id", description = "Endpoint para buscar os dados de uma pessoa cadastrada no sistema")
	public ResponseEntity<DadosDePessoa> buscarPorId(@PathVariable Long id) {
		DadosDePessoa dadosDePessoa = buscaDadosDePessoas.buscarPorId(id);
		return ResponseEntity.ok().body(dadosDePessoa);
	}

	@GetMapping(value = "/cep/{cep}")
	@Cacheable(value = "buscarPorCep")
	@Operation(summary = "Buscar Dados de Pessoas pelo Cep", description = "Endpoint para buscar os dados de pessoas cadastradas no sistema "
			+ "pelo Cep")
	public ResponseEntity<Page<DadosDePessoa>> buscarPorCep(@PathVariable String cep, Pageable paginacao) {
		Page<DadosDePessoa> dadosDePessoas = buscaDadosDePessoas.buscarPorCep(cep, paginacao);
		return ResponseEntity.ok().body(dadosDePessoas);
	}

	@GetMapping(value = "/cidade/{cidade}")
	@Cacheable(value = "buscarPorCidade")
	@Operation(summary = "Buscar Dados de Pessoas pela Cidade", description = "Endpoint para buscar os dados de todas as pessoas cadastradas "
			+ "no sistema pela cidade")
	public ResponseEntity<Page<DadosDePessoa>> buscarPorCidade(@PathVariable String cidade, Pageable paginacao) {
		Page<DadosDePessoa> dadosDePessoas = buscaDadosDePessoas.buscarPorCidade(cidade, paginacao);
		return ResponseEntity.ok().body(dadosDePessoas);
	}

	@GetMapping(value = "/nome/{nome}")
	@Cacheable(value = "buscarPorNome")
	@Operation(summary = "Buscar Dados de Pessoas pela nome", description = "Endpoint para buscar os dados de todas as pessoas cadastradas "
			+ "no sistema pelo nome. A busca é realizada buscando todos as pessoas que contenham o nome desejado, ou parte dele")
	public ResponseEntity<List<DadosDePessoa>> buscarPorNome(@PathVariable String nome) {
		List<DadosDePessoa> dadosDePessoas = buscaDadosDePessoas.buscarPorNome(nome);
		return ResponseEntity.ok().body(dadosDePessoas);
	}

	@GetMapping
	@Cacheable(value = "buscarTodas")
	@Operation(summary = "Buscar Dados de Pessoas", description = "Endpoint para buscar os dados de todas as pessoas cadastradas no sistema")
	public ResponseEntity<Page<DadosDePessoa>> buscarTodas(Pageable paginacao) {
		Page<DadosDePessoa> dadosDePessoas = buscaDadosDePessoas.buscarTodas(paginacao);
		return ResponseEntity.ok().body(dadosDePessoas);
	}

}
