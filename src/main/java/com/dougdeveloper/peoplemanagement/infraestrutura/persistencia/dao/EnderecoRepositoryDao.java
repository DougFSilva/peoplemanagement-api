package com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.entity.EnderecoEntity;

@Repository
public interface EnderecoRepositoryDao extends JpaRepository<Long, EnderecoEntity> {

}
