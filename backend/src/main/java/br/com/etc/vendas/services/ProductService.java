package br.com.etc.vendas.services;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.etc.vendas.dto.CategoryDTO;
import br.com.etc.vendas.dto.ProductDTO;
import br.com.etc.vendas.entities.Category;
import br.com.etc.vendas.entities.Product;
import br.com.etc.vendas.repositories.CategoryRepository;
import br.com.etc.vendas.repositories.ProductRepository;
import br.com.etc.vendas.services.exceptions.EntidadeNaoEncontradaException;
import br.com.etc.vendas.services.exceptions.ExcecaoDeBaseDeDados;

@Service
public class ProductService {
	
	    private final ProductRepository productRepository;
	    
	    private final CategoryRepository categoryRepository;
	    
	    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
	    	this.productRepository = productRepository;
	    	this.categoryRepository = categoryRepository;
		} 
	    	
	    
	    @Transactional(readOnly = true)
	    public ProductDTO findById(Long id) {
	    	Optional<Product> obj = productRepository.findById(id);
	    	Product entity = obj.orElseThrow(() -> new EntidadeNaoEncontradaException("Não encontrado"));
	    	return new ProductDTO(entity);
	    }
	    
	    
	    @Transactional(readOnly = true)
	    public Page<ProductDTO> findAllPaged(Pageable pageable) {
	    	Page<Product> page = productRepository.findAll(pageable);
	    	return page.map(x -> new ProductDTO(x));
	    }
	   
	    @Transactional
	    public ProductDTO insert(ProductDTO dto) {
	        Product entity = new Product();
	        copyDtoToEntity(dto, entity);
	        
//	        entity.setName(dto.getName());
//	        entity.setDescription(dto.getDescription());
//	        entity.setPrice(dto.getPrice());
//	        entity.setImgUrl(dto.getImgUrl());
//	        entity.setDate(dto.getDate());

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
	    
	    public void delete(long id) {
	    	Product entity = productRepository.findById(id)
	    			.orElseThrow(() -> new EntidadeNaoEncontradaException("Produto não existe"));
	    	try {
				productRepository.delete(entity);
			} catch (DataIntegrityViolationException e) {
				throw new ExcecaoDeBaseDeDados("Violação de integridade");
			}
	    }
	    

	    // 👇 AQUI
	    private void copyDtoToEntity(ProductDTO dto, Product entity) {
	        entity.setName(dto.getName());
	        entity.setDescription(dto.getDescription());
	        entity.setDate(dto.getDate());
	        entity.setImgUrl(dto.getImgUrl());
	        entity.setPrice(dto.getPrice());
	        
	        entity.getCategories().clear();

	        for (CategoryDTO catDto : dto.getCategories()) {
	            Category category = categoryRepository.getReferenceById(catDto.getId());
	            entity.getCategories().add(category);
	        }
	    }
}
