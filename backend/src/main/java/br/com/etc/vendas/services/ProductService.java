package br.com.etc.vendas.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.etc.vendas.dto.CategoryDTO;
import br.com.etc.vendas.dto.ProductDTO;
import br.com.etc.vendas.entities.Category;
import br.com.etc.vendas.entities.Product;
import br.com.etc.vendas.repositories.CategoryRepository;
import br.com.etc.vendas.repositories.ProductRepository;

@Service
public class ProductService {
	
	    private final ProductRepository productRepository;
	    
	    private final CategoryRepository categoryRepository;
	    
	    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
	    	this.productRepository = productRepository;
	    	this.categoryRepository = categoryRepository;
		} 
	    	
	   
	    @Transactional
	    public ProductDTO insert(ProductDTO dto) {
	        Product entity = new Product();
	        entity.setName(dto.getName());
	        entity.setDescription(dto.getDescription());
	        entity.setPrice(dto.getPrice());
	        entity.setImgUrl(dto.getImgUrl());
	        entity.setDate(dto.getDate());

	        for (CategoryDTO catDto : dto.getCategories()) {
	            Category category = categoryRepository.getReferenceById(catDto.getId());
	            entity.getCategories().add(category);
	        }

	        entity = productRepository.save(entity);

	        return new ProductDTO(entity);
	    }
	    
	    @Transactional
	    public ProductDTO update(Long id, ProductDTO dto) {
	        Product entity = productRepository.getReferenceById(id);
	        copyDtoToEntity(dto, entity);
	        entity = productRepository.save(entity);
	        return new ProductDTO(entity);
	    }

	    // 👇 AQUI
	    private void copyDtoToEntity(ProductDTO dto, Product entity) {

	        entity.setName(dto.getName());
	        entity.setDescription(dto.getDescription());
	        entity.setPrice(dto.getPrice());

	        entity.getCategories().clear();

	        for (CategoryDTO catDto : dto.getCategories()) {
	            Category category = categoryRepository.getReferenceById(catDto.getId());
	            entity.getCategories().add(category);
	        }
	    }
}
