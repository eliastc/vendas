package br.com.etc.vendas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.etc.vendas.dto.CategoryDTO;
import br.com.etc.vendas.entities.Category;
import br.com.etc.vendas.repositories.CategoryRepository;



@Service
public class CategoryService {
	
	private final CategoryRepository repo;
	
	public CategoryService(CategoryRepository repo) {
		this.repo = repo;
	}
	
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repo.findAll();
		
		return list.stream().map(x -> new CategoryDTO(x))
				.collect(Collectors.toList());
		
		
//		posso fazer assim
//		List<CategoryDTO> listDto = new ArrayList<>();
//		for(Category cat : list) {
//			listDto.add(new CategoryDTO(cat));
//		}
//		 return liswtDto;
	}

}
