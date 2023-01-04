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

import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.AdicionarEnderecoAPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarDadosDePessoaPorId;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarPessoaPorId;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarDadosDePessoasPorCep;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarDadosDePessoasPorCidade;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarDadosDePessoasPorNome;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.BuscarDadosDeTodasPessoas;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.CriarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.DadosCriarEndereco;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.DadosCriarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.DadosDePessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.DadosEditarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.DeletarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.EditarPessoa;
import com.dougdeveloper.peoplemanagement.aplicacao.pessoa.RemoverEnderecoDaPessoa;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/pessoa")
@EnableCaching
public class PessoaController {

	@Autowired
	private AdicionarEnderecoAPessoa adicionarEnderecoAPessoa;
	
	@Autowired
	private BuscarPessoaPorId buscarPessoaPorId;
	
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
	private EditarPessoa editarPessoa;
	
	@Autowired
	private RemoverEnderecoDaPessoa removerEnderecoDaPessoa;
	
	@PostMapping
	@CacheEvict(value = {"buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas"})
	@Transactional
	public ResponseEntity<Pessoa> criarPessoa(@RequestBody @Valid DadosCriarPessoa dados){
		Pessoa pessoa = criarPessoa.executar(dados);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	@CacheEvict(value = {"buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas"})
	@Transactional
	public ResponseEntity<Void> deletarPessoa(@PathVariable Long id){
		deletarPessoa.executar(id, buscarPessoaPorId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	@CacheEvict(value = {"buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas"})
	@Transactional
	public ResponseEntity<Pessoa> editarPessoa(@PathVariable Long id, @RequestBody @Valid DadosEditarPessoa dados){
		Pessoa pessoa = editarPessoa.executar(id, dados, buscarPessoaPorId);
		return ResponseEntity.ok().body(pessoa);
	}
	
	@PutMapping(value = "/{id}/adicionar-endereco")
	@CacheEvict(value = {"buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas"})
	@Transactional
	public ResponseEntity<Pessoa> adicionarEndereco(@PathVariable Long id, @RequestBody @Valid DadosCriarEndereco dados){
		Pessoa pessoa = adicionarEnderecoAPessoa.executar(id, dados, buscarPessoaPorId);
		return ResponseEntity.ok().body(pessoa);
	}
	
	@PutMapping(value = "/{id}/remover-endereco/{enderecoId}")
	@CacheEvict(value = {"buscarPorCep", "buscarPorCidade", "buscarPorNome", "buscarTodas"})
	@Transactional
	public ResponseEntity<Pessoa> removerEndereco(@PathVariable Long id, @PathVariable Long enderecoId){
		Pessoa pessoa = removerEnderecoDaPessoa.executar(id, enderecoId, buscarPessoaPorId);
		return ResponseEntity.ok().body(pessoa);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DadosDePessoa> buscarPorId(@PathVariable Long id){
		DadosDePessoa dadosDePessoa = buscarDadosDePessoaPorId.executar(id);
		return ResponseEntity.ok().body(dadosDePessoa);
	}
	
	@GetMapping(value = "/cep/{cep}")
	@Cacheable(value = "buscarPorCep")
	public ResponseEntity<Page<DadosDePessoa>> buscarPorCep(@PathVariable String cep, Pageable paginacao){
		Page<DadosDePessoa> dadosDePessoas = buscarPessoasPorCep.executar(cep, paginacao);
		return ResponseEntity.ok().body(dadosDePessoas);
	}
	
	@GetMapping(value = "/cidade/{cidade}")
	@Cacheable(value = "buscarPorCidade")
	public ResponseEntity<Page<DadosDePessoa>> buscarPorCidade(@PathVariable String cidade, Pageable paginacao){
		Page<DadosDePessoa> dadosDePessoas = buscarPessoasPorCidade.executar(cidade, paginacao);
		return ResponseEntity.ok().body(dadosDePessoas);
	}
	
	@GetMapping(value = "/nome/{nome}")
	@Cacheable(value = "buscarPorNome")
	public ResponseEntity<List<DadosDePessoa>> buscarPorNome(@PathVariable String nome){
		List<DadosDePessoa> dadosDePessoas = buscarPessoasPorNome.executar(nome);
		return ResponseEntity.ok().body(dadosDePessoas);
	}
	
	@GetMapping
	@Cacheable(value = "buscarTodas")
	public ResponseEntity<Page<DadosDePessoa>> buscarTodas(Pageable paginacao){
		Page<DadosDePessoa> dadosDePessoas = buscarTodasPessoas.executar(paginacao);
		return ResponseEntity.ok().body(dadosDePessoas);
	}
	
}
