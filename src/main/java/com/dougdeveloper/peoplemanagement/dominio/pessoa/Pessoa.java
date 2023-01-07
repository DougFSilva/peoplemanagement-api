package com.dougdeveloper.peoplemanagement.dominio.pessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * A classe <b>Pessoa</b> define o modelo de uma pessoa.
 * @author Douglas Ferreira da Silva
 * @since Janeiro 2023
 * @version 1.0
 */
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@ToString
public class Pessoa {

	private Long id;

	private String nome;

	private LocalDate dataNascimento;

	@Setter(value = AccessLevel.PRIVATE)
	private List<Endereco> enderecos = new ArrayList<>();

	public Pessoa(String nome, LocalDate dataNascimento) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}

	public void adicionarEndereco(Endereco novoEndereco) {
		if (novoEndereco.isPrincipal()) {
			this.getEnderecos().forEach(endereco -> endereco.setPrincipal(false));
		}
		this.enderecos.add(novoEndereco);
	}

	public void removerEndereco(Endereco endereco) {
		this.enderecos.remove(endereco);
	}

	public List<Endereco> getEnderecos() {
		return Collections.unmodifiableList(this.enderecos);
	}
}
