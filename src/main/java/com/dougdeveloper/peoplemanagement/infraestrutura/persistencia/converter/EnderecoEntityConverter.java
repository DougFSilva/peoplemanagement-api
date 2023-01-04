package com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.converter;

import org.springframework.stereotype.Service;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Cep;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;
import com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.entity.CepEntity;
import com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.entity.EnderecoEntity;

@Service
public class EnderecoEntityConverter {

	public EnderecoEntity paraEnderecoEntity(Endereco endereco) {
		return new EnderecoEntity(endereco.getId(), endereco.getLogradouro(),
				new CepEntity(endereco.getCep().getDigitos()), endereco.getNumero(), endereco.getCidade(),
				endereco.isPrincipal());
	}

	public Endereco paraEndereco(EnderecoEntity entity) {
		return new Endereco(entity.getId(), entity.getLogradouro(), new Cep(entity.getCep().getDigitos()),
				entity.getNumero(), entity.getCidade(), entity.isPrincipal());
	}
}
