package com.dougdeveloper.peoplemanagement.dominio.pessoa;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
public class Endereco {
	
	private Long id;
	
	private String logradouro;
	
	private Cep cep;
	
	private String numero;
	
	private String cidade;
	
	private boolean principal;
	
	public Endereco(String logradouro, String digitosCep, String numero, String cidade, boolean principal) {
		this.logradouro = logradouro;
		this.cep = new Cep(digitosCep);
		this.numero = numero;
		this.cidade = cidade;
		this.principal = principal;
	}
}
