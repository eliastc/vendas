package br.com.etc.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.etc.vendas.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
