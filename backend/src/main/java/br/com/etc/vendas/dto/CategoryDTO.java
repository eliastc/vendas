package br.com.etc.vendas.dto;

import br.com.etc.vendas.entities.Category;

public class CategoryDTO {

	private Long id;
	private String nome;
	
	public CategoryDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public CategoryDTO(Category entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
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
}
