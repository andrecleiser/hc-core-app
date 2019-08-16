package br.org.com.hc.core.exception.util;

public class ValidacaoParametrosApiErro {
	private String localDoErro;
	private String mensagemErro;
	
	public ValidacaoParametrosApiErro(String localDoErro, String mensagemErro) {
		this.localDoErro = localDoErro;
		this.mensagemErro = mensagemErro;
	}

	public String getLocalDoErro() {
		return localDoErro;
	}

	public String getMensagemErro() {
		return mensagemErro;
	}
}
