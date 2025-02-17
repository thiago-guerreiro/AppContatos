package com.tgm.AppContatos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = true)
	private String endereco;
	
	@Column(nullable = true)
	private String cep;
	
	@Column(nullable = true)
	private String cidade;
	
	@Column(nullable = true)
	@Size(min = 2, max = 2)
	private String uf;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties("pessoa")
	private List<Contato> contatos;

	public Pessoa() {}

	public Pessoa(Long id, String nome, String endereco, String cep, String cidade, String uf, List<Contato> contatos) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
		this.contatos = contatos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	@Override
	public String toString() {
		return "[" + 
				"Nome: "     + this.nome     + ", " + 
				"Endereco: " + this.endereco + ", " + 
				"CEP: "      + this.cep      + ", " + 
				"Cidade: "   + this.cidade   + ", " +
				"UF: "       + this.uf       + ", " + 
				"Contatos: " + this.contatos + "]";
	}
	
}
