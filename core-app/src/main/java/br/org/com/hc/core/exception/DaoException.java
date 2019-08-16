package br.org.com.hc.core.exception;

public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 3448339145638415981L;
	
	public DaoException(String mensagem) {
		super(mensagem);
	}
}
