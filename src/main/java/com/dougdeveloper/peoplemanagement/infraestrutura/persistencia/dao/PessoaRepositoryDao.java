package com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.entity.PessoaEntity;

/**
 * A interface <b>PessoaRepositoryDao</b> é uma Data Access Object responsável por encapsular o acesso ao banco de dados
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
@Repository
public interface PessoaRepositoryDao extends JpaRepository<PessoaEntity, Long> {

	Page<PessoaEntity> findAllByNomeContainingIgnoreCase(String nome, Pageable paginacao);

	Page<PessoaEntity> findAllByEnderecosCepDigitos(String cep, Pageable paginacao);

	Page<PessoaEntity> findAllByEnderecosCidadeContainingIgnoreCase(String cidade, Pageable paginacao);

}
