package br.com.etc.vendas.services.exceptions;

public class ExcecaoDeBaseDeDados extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExcecaoDeBaseDeDados(String msg) {
		super(msg);
	}
}
