package com.dougdeveloper.peoplemanagement.aplicacao.pessoa.validacao;

import java.util.List;

import com.dougdeveloper.peoplemanagement.dominio.exception.ContagemDeEnderecosPrincipaisException;
import com.dougdeveloper.peoplemanagement.dominio.pessoa.Endereco;

public class ValidarEnderecos {

	public static void validar(List<Endereco> enderecos) {
		long contagemDeEnderecosPrincipais = enderecos.stream().filter(endereco -> endereco.isPrincipal()).count();
		if(contagemDeEnderecosPrincipais > 1) {
			throw new ContagemDeEnderecosPrincipaisException("Somente um endereço deve ser anotado como principal!");
		}
	}

}
