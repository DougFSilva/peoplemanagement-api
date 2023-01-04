package com.dougdeveloper.peoplemanagement.dominio.pessoa;

import java.time.LocalDate;
import java.util.List;

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
public class Pessoa {
	
	private Long id;

	private String nome;
	
	private LocalDate dataNascimento;
	
	private List<Endereco> enderecos;
}
