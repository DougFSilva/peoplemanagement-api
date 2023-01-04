package com.dougdeveloper.peoplemanagement.dominio.pessoa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnderecoRepository {

	Endereco criar(Endereco endereco);

	Endereco deletar(Endereco endereco);

	Endereco editar(Endereco enderecoEditado);

	Optional<Endereco> buscarPorId(Long id);
	
	List<Endereco> buscarPorPessoa(Pessoa pessoa);

	Page<Pessoa> listar(Pageable paginacao);
}
