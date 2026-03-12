package br.com.etc.vendas.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.etc.vendas.repositories.ProductRepository;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {
	
	@InjectMocks
	private ProductService service;
	
	@Mock
	private ProductRepository repo;
	
	private long existingId;
	private long nonExistingId;
	
	@BeforeEach
	void setup() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		Mockito.doNothing().when(repo).deleteById(existingId);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repo)
			.deleteById(nonExistingId);
		
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		
		Assertions.assertDoesNotThrow(() -> {
			service.delete(nonExistingId);
			
		});
		
		Mockito.verify(repo, Mockito.times(1)).deleteById(nonExistingId);
	}
	
}
