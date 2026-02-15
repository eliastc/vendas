package br.com.etc.vendas.controllers.exceptions;

import java.time.Instant;

public class ErroPadrao {
	
	private Instant timesstamp;
	private Integer status;
	private String erro;
	private String mensagem;
	private String caminho;
	
	public ErroPadrao() {		
	}

	public Instant getTimesstamp() {
		return timesstamp;
	}

	public void setTimesstamp(Instant timesstamp) {
		this.timesstamp = timesstamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
}
