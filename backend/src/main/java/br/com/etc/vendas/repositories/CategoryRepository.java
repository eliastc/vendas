package br.com.etc.vendas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.etc.vendas.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
