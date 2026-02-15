package br.com.etc.vendas.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.etc.vendas.services.exceptions.EntidadeNaoEncontradaException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ManipuladorDeExceptions {
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<ErroPadrao> EntidadeNaoEncontrada
		(EntidadeNaoEncontradaException e, HttpServletRequest request) {
		ErroPadrao erro = new ErroPadrao();
		erro.setTimesstamp(Instant.now());
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setErro("Recurso não encontrado");
		erro.setMensagem(e.getMessage());
		erro.setCaminho(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
				
	}
}
