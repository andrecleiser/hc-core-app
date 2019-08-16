package br.org.com.hc.core.exception;

public class EntidadeNaoEncontradaException extends DaoException {

	private static final long serialVersionUID = -3025176493013679030L;

	public EntidadeNaoEncontradaException(String nomeEntidade) {
		super(nomeEntidade.concat(" não encontrado(a)!"));
	}
}
