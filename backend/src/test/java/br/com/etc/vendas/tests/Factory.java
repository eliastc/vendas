package br.com.etc.vendas.tests;

import java.math.BigDecimal;
import java.time.Instant;

import br.com.etc.vendas.dto.ProductDTO;
import br.com.etc.vendas.entities.Category;
import br.com.etc.vendas.entities.Product;

public class Factory {
	
	public static Product createProduct() {
		Product product = new Product(1L, "Phone", "Good Phone", new BigDecimal(800.0), "https://img/24-big.jpg", Instant.parse("2026-03-14T10:00:00Z"));
		product.getCategories().add(new Category(2L, "Electronics"));
		return product;	
	}
	
	public static ProductDTO createProductDTO() {
		Product product = createProduct();
		return new ProductDTO(product, product.getCategories());
	}
	

}
