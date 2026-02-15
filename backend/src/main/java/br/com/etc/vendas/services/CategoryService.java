package br.com.etc.vendas.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.etc.vendas.dto.CategoryDTO;
import br.com.etc.vendas.entities.Category;
import br.com.etc.vendas.repositories.CategoryRepository;
import br.com.etc.vendas.services.exceptions.EntidadeNaoEncontradaException;
import br.com.etc.vendas.services.exceptions.ExcecaoDeBaseDeDados;
import jakarta.persistence.EntityNotFoundException;



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

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repo.findById(id);
		Category entity = obj.
				orElseThrow(() -> new EntidadeNaoEncontradaException("Entidade não encontrada!"));
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setNome(dto.getNome());
		entity = repo.save(entity);
		return new CategoryDTO(entity);
	}

	
	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
			Category entity = repo.getReferenceById(id);
			entity.setNome(dto.getNome());
			entity = repo.save(entity);
			return new CategoryDTO(entity);
			
		} catch (EntityNotFoundException e) {
			throw new EntidadeNaoEncontradaException("Categoria não encontrada!");
		}
	}

//	Não traz respostas quando deletada o obj, pq no banco não houb=ve alteração na linha
//	public void delete(Long id) {
//		if (!repo.existsById(id)) {
//	        throw new EntidadeNaoEncontradaException("Categoria não encontrada! " + id);
//	    }
//		try {
//			repo.deleteById(id);
//		} catch (EmptyResultDataAccessException e) {
//			throw new EntidadeNaoEncontradaException("Categoria não encontrada! " + id);
//		} catch (DataIntegrityViolationException e ) {
//			throw new  ExcecaoDeBaseDeDados("Violação de integridade do banco de dados! ");
//		}		
//	}
	
	public void delete(Long id) {
	    Category entity = repo.findById(id)
	        .orElseThrow(() -> 
	            new EntidadeNaoEncontradaException("Categoria não existe! " + id)
	        );

	    try {
	        repo.delete(entity);
	    } catch (DataIntegrityViolationException e) {
	        throw new ExcecaoDeBaseDeDados("Violação de integridade do banco de dados!");
	    }
	}

}
