package br.com.etc.vendas.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.etc.vendas.dto.CategoryDTO;
import br.com.etc.vendas.services.CategoryService;

@RestController
@RequestMapping("/categorias")
public class CategoryController {
	
	private final CategoryService service;
	
	
	public CategoryController(CategoryService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<CategoryDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
