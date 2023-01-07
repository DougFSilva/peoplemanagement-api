package com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.converter;

import org.springframework.stereotype.Service;

import com.dougdeveloper.peoplemanagement.dominio.pessoa.Cep;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;
import com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.entity.CepEntity;
import com.dougdeveloper.peoplemanagement.infraestrutura.persistencia.entity.EnderecoEntity;

/**
 * A classe <b>EnderecoEntityConverter</b> possui os métodos responsáveis pela conversão de um objeto do tipo <b>Endereco</b> para o tipo
 * <b>EnderecoEntity</b> e vice-versa.
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
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
