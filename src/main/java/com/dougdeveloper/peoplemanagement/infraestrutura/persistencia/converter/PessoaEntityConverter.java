package com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Pessoa;
import com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.entity.EnderecoEntity;
import com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.entity.PessoaEntity;

@Service
public class PessoaEntityConverter {

	@Autowired
	private EnderecoEntityConverter enderecoEntityConverter;

	public PessoaEntity paraPessoaEntity(Pessoa pessoa) {
		List<EnderecoEntity> enderecosEntity = pessoa.getEnderecos()
				.stream()
				.map(endereco -> enderecoEntityConverter.paraEnderecoEntity(endereco))
				.toList();
		return new PessoaEntity(pessoa.getId(), pessoa.getNome(), pessoa.getDataNascimento(), enderecosEntity);
	}

	public Pessoa paraPessoa(PessoaEntity entity) {
		List<Endereco> enderecos = entity.getEnderecos()
				.stream()
				.map(enderecoEntity -> enderecoEntityConverter.paraEndereco(enderecoEntity))
				.collect(Collectors.toList());
		return new Pessoa(entity.getId(), entity.getNome(), entity.getDataNascimento(), enderecos);
	}
}
