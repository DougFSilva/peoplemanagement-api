package com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.PessoaRepository;

@Repository
public interface PessoaRepositoryDao extends JpaRepository<Long, PessoaRepository> {

}
