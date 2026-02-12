package br.com.etc.vendas.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.etc.vendas.entities.Category;
import br.com.etc.vendas.repositories.CategoryRepository;



@Service
public class CategoryService {
	
	private final CategoryRepository repo;
	
	public CategoryService(CategoryRepository repo) {
		this.repo = repo;
	}
	
	@Transactional(readOnly = true)
	public List<Category> findAll() {
		return repo.findAll();
	}

}
